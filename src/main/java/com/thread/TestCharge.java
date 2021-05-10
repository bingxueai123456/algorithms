package com.thread;

import java.util.concurrent.Exchanger;

/**
 * @Auther: eclair
 * @Date: 2021/3/31 22:16
 * @Description:
 */
public class TestCharge {
	public static void main(String[] args) {
		Exchanger<String> s = new Exchanger<>();
		new Thread(()->{
			try {
				String nihao = s.exchange("nihao");
				System.out.println(nihao+"||||||");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("完成");
		}).start();

		new Thread(()->{
			try {
//				String nihao = s.exchange("wobuhao");
//				System.out.println(nihao+"xxxxx");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("完成");
		}).start();
	}
}
