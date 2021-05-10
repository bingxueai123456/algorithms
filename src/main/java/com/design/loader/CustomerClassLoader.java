package com.design.loader;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Auther: eclair
 * @Date: 2019/9/4 12:40
 * @Description:
 */
public class CustomerClassLoader extends URLClassLoader {
	private static final String PACKAGE_NAME = "com.ice.test";

	public CustomerClassLoader(URL[] urls) {
		super(urls);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> loadedClass = findLoadedClass(name);
		if (Objects.nonNull(loadedClass)) {
			return loadedClass;
		}
		if (name.startsWith(PACKAGE_NAME)) {
			byte[] b = new byte[0];
			try {
				b = loadData(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return super.defineClass(name, b, 0, b.length);
		} else {
			return super.findClass(name);
		}
	}

	private byte[] loadData(String name) throws Exception {
		FileInputStream fis = new FileInputStream("E://Programmer.class");
		byte b[] = new byte[2024];
		int len = fis.read(b);

		return Arrays.copyOf(b, len);
	}
}
