package com.thread;

import java.util.concurrent.Semaphore;

/**
 * @Auther: eclair
 * @Date: 2021/3/31 22:05
 * @Description:
 */
public class TestSemaphore {
	int i = 20;

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				System.out.println("开始取票！" + System.currentTimeMillis());
				try {
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("完成"  + System.currentTimeMillis());
				semaphore.release();
			}).start();
		}
	}
}
