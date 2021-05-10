package com.thread;

import org.apache.flink.shaded.guava18.com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.SynchronousQueue;

/**
 * @Auther: eclair
 * @Date: 2020/7/29 21:56
 * @Description:
 */
public class TestQueue {
	public static void main(String[] args) {
//		ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
//		concurrentLinkedQueue.add(1);
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		Integer poll = queue.poll();
		System.out.println(poll);
//		new Thread(() -> {
//			while (true) {
//				try {
//					System.out.println("fangru");
//					queue.put(1);
//					Thread.sleep(1000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		new Thread(()->{
//			while(true) {
//				try {
//					System.out.println("nihao");
//					Integer take = queue.poll(300, TimeUnit.MILLISECONDS);
//					System.out.println(take);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();

//		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
//		queue.add(1);
//		queue.add(2);
//		queue.add(3);
//		queue.add(4);
//		queue.add(5);
//
//		ArrayList<Integer> objects = Lists.newArrayList();
//		int i = queue.drainTo(objects, 2);
//		System.out.println(objects);
//		System.out.println(queue);
//		S s = new S();
//		Class<? extends S> aClass = s.getClass();
//		Field[] declaredFields = aClass.getDeclaredFields();
//		for (Field field : declaredFields) {
//			System.out.println(field);
//		}
		Map<Object, Object> objectObjectHashMap = Maps.newHashMap();
	}
}

class P {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
class S extends P{
	private String age;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
