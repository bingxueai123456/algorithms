package com.design.abstracted;

import java.lang.reflect.Proxy;

/**
 * @Auther: eclair
 * @Date: 2019/9/4 12:28
 * @Description:
 */
public class TestMain {
	public static void main(String[] args) {
		Cpu o =(Cpu) Proxy.newProxyInstance(TestMain.class.getClassLoader(), new Class[]{Cpu.class}, (p, m, s) -> {
			if (m.getName().equalsIgnoreCase("calculate")) {
				System.out.println("ok");
			}
			return null;
		});
		o.calculate();
	}
}
