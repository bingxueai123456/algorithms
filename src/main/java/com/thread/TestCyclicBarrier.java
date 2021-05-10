package com.thread;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: eclair
 * @Date: 2021/3/31 22:11
 * @Description:
 */
public class TestCyclicBarrier {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
		for(int i = 0;i < 4;i++) {
			new Thread(()->{
				System.out.println("开始");
				try {
					Thread.sleep(RandomUtils.nextInt(1,1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("结束");

				try {
					Thread.sleep(RandomUtils.nextInt(1,1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("再一次");
			}).start();
		}
	}
}
