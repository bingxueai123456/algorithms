package com.design.loader;

import java.net.URL;

/**
 * @Auther: eclair
 * @Date: 2019/9/4 12:41
 * @Description:
 */
public class TestMain {
	public static void main(String[] args) throws Exception {
		CustomerClassLoader customerClassLoader = new CustomerClassLoader(new URL[1]);
		Class<?> aClass = customerClassLoader.findClass("com.ice.test.Programmer");
		System.out.println(aClass);

	}
}
