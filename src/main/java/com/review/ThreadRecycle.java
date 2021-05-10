package com.review;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: eclair
 * @Date: 2019/8/30 16:20
 * @Description:
 */
public class ThreadRecycle {
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		AtomicBoolean firstFlag = new AtomicBoolean(true);
		Condition condition = lock.newCondition();
		Thread t = new Thread(() -> {
			lock.lock();
			try {
				for (int i = 0; i < 2; i++) {
					if(!firstFlag.get()) {
						try {
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for (int j = 1; j <= 10; j++) {
						System.out.println("子线程循环" + j);
					}
					condition.signal();
					firstFlag.set(false);

				}
			} finally {
				lock.unlock();
			}
		});
		t.start();
		Thread.sleep(1000L);
		lock.lock();
		try {
			for (int i = 0; i < 2; i++) {
				if(firstFlag.get()) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 50; j++) {
					System.out.println("主线程循环" + j);
				}
				condition.signal();
				firstFlag.set(true);
			}
		} finally {
			lock.unlock();
		}

	}
}
