package com.reactor;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: eclair
 * @Date: 2019/5/13 21:32
 * @Description:
 */
public class ReactorTest {
	@Test
	public void testError1() {
		Flux.range(1, 6).map(i -> 10 / (i - 3))
				.onErrorReturn(0).
				map(i -> i * i)
				.subscribe(System.out::println, System.out::println);
	}

	@Test
	public void testError2() {
		Flux.range(1, 6).map(i -> 10 / (i - 3))
				.onErrorResume(e -> {
					e.printStackTrace();
					return Mono.just(new Random().nextInt(5));
				}).
				map(i -> i * i)
				.subscribe(System.out::println, System.out::println);
	}

	@Test
	public void testError3() {
		Flux.range(1, 6).map(i -> 10 / (i - 3))
				.onErrorMap(e -> new RuntimeException(e.getMessage()))
				.onErrorMap(e -> new IllegalAccessError(e.getMessage()))
				.subscribe(System.out::println, System.out::println);
	}

	@Test
	public void testError4() throws InterruptedException {
		Flux.range(1, 6).map(i -> 10 / (i - 3))
				.onErrorMap(e -> new RuntimeException(e.getMessage()))
//				.onErrorMap(e -> new IllegalAccessError(e.getMessage()))
//				.doFinally(type ->
//						System.out.println(type))
				.retry(1)
				.subscribe(System.out::println, System.out::println);
		Thread.sleep(2000);
	}

	@Test
	public void testBackPressure() {
		Flux.range(1, 5)
				.doOnRequest(n -> System.out.println("request " + n + " values..."))
				.subscribe(new BaseSubscriber<Integer>() {
					@Override
					protected void hookOnSubscribe(Subscription subscription) {
						System.out.println("Subscribed and make a request...");
						request(1); // 5
					}

					@Override
					protected void hookOnNext(Integer value) {
						try {
							TimeUnit.SECONDS.sleep(1);  // 7
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Get value [" + value + "]");    // 8
						request(2); // 9
					}
				});
	}

	@Test
	public void testInterval() throws InterruptedException {
		Flux.interval(Duration.ofMillis(500)).map(e -> {
			System.out.println(e);
			return e;
		}).subscribe(System.out::println);
		Thread.sleep(2000L);
	}

	@Test
	public void testDuration() throws InterruptedException {
//		Mono.just("Hellow world").delayElement(Duration.ofMillis(2000))
//				.subscribe(System.out::println);
//		Thread.sleep(3000L);
		String str = "Iam;ok;fed;sdf;";
		String[] split = str.split(";");
		Arrays.stream(split).map(e -> e.split("")).collect(Collectors.toList()).stream();

		List<String> collect = Arrays.stream(split).flatMap(e -> Stream.of(e.split(""))).collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void testGenerate() {
		Flux.generate(
				() -> 1,
				(count, sink) -> {
					sink.next(count + " : " + new Date());
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (count >= 5) {
						sink.complete();
					}
					return count + 1;
				}, System.out::println)     // 1
				.subscribe(System.out::println);
	}
	@Test
	public void testCreate() throws InterruptedException {
		MyEventSource eventSource = new MyEventSource();    // 1
		Flux.create(sink -> {
					eventSource.register(new MyEventListener() {    // 2
						@Override
						public void onNewEvent(MyEventSource.MyEvent event) {
							sink.next(event);       // 3
						}

						@Override
						public void onEventStopped() {
							sink.complete();        // 4
						}
					});
				}
		).subscribe(System.out::println);
		for (int i = 0; i < 20; i++) {  // 6
			Random random = new Random();
			TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
			eventSource.newEvent(new MyEventSource.MyEvent(new Date(), "Event-" + i));
		}
		eventSource.eventStopped(); // 7

	}

	public static void main(String[] args) throws InterruptedException {
		MyEventSource eventSource = new MyEventSource();    // 1
		Flux.create(sink -> {
			System.out.println("sink:" + Thread.currentThread().getName());
					eventSource.register(new MyEventListener() {    // 2
						@Override
						public void onNewEvent(MyEventSource.MyEvent event) {
							System.out.println("next:" + Thread.currentThread().getName());
							sink.next(event);       // 3
						}

						@Override
						public void onEventStopped() {
							System.out.println("complete:" + Thread.currentThread().getName());
							sink.complete();        // 4
						}
					});
				}
		).subscribe(System.out::println);
		for (int i = 0; i < 20; i++) {  // 6
			Random random = new Random();
			TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
			eventSource.newEvent(new MyEventSource.MyEvent(new Date(), "Event-" + i));
		}
		eventSource.eventStopped(); // 7

	}

	@Test
	public void test1() throws InterruptedException {
		Flux.range(1, 100)
				.parallel(2)
				.runOn(Schedulers.newParallel("hello", 3))
				.log().subscribe();
		TimeUnit.MILLISECONDS.sleep(10);
	}

	@Test
	public void testTransform() {
		Function<Flux<String>, Flux<String>> filterAndMap =
				f -> f.filter(color -> !color.equals("orange"))
						.map(String::toUpperCase);

		Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
				.doOnNext(System.out::println)
				.transform(filterAndMap)
				.subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: "+d));
	}

	@Test
	public void testCompose() {
		AtomicInteger ai = new AtomicInteger();
		Function<Flux<String>, Flux<String>> filterAndMap = f -> {
			if (ai.incrementAndGet() == 1) {
				return f.filter(color -> !color.equals("orange"))
						.map(String::toUpperCase);
			}
			return f.filter(color -> !color.equals("purple"))
					.map(String::toUpperCase);
		};

		Flux<String> composedFlux =
				Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
						.doOnNext(System.out::println)
						.compose(filterAndMap);

		composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
		composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
	}

}

