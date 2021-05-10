package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.DAYS;

/**
 * @Auther: eclair
 * @Date: 2020/3/31 23:00
 * @Description:
 */
public class TestThread {
	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING = -1 << COUNT_BITS;
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	private static final int STOP = 1 << COUNT_BITS;
	private static final int TIDYING = 2 << COUNT_BITS;
	private static final int TERMINATED = 3 << COUNT_BITS;

	private static AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

	private static int runStateOf(int c) {
		return c & ~CAPACITY;
	}

	private static int workerCountOf(int c) {
		return c & CAPACITY;
	}
	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
		new Thread(() -> {

			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean offer = queue.offer(1);
			System.out.println(offer);
		}).start();
		new Thread(() -> {
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				System.out.println(queue.poll(1, DAYS));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}
}

class A {
	public synchronized void fun() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class B extends A {

}
