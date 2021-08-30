package com.concurrency;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: eclair
 * @Date: 2021/5/16 22:17
 * @Description:
 */
public class TestObject {
	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(5000L);
		Object o = new Object();
		String s = ClassLayout.parseInstance(o).toPrintable();
		System.out.println(s);
		synchronized (o){
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
		}
	}
}
