package com.design.loader;

public class MyClassLoader extends ClassLoader {

	public Class<?> defineMyClass(byte[] b, int off, int len) {
		Class<?> aClass = super.defineClass(b, off, len);
		super.resolveClass(aClass);
		return aClass;
	}

	public Class<?> defineMyClass(String name, byte[] b, int off, int len) {
		return super.defineClass(name, b, off, len);
	}

}