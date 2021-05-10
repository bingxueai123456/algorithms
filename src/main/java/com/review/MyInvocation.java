package com.review;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 11:26
 * @Description:
 */
public class MyInvocation<T> implements InvocationHandler {
	private T t;

	public MyInvocation(T t) {
		this.t = t;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invoke = null;
		if (Objects.equals("fun", method.getName())) {
			System.out.println("into proxy");
			invoke = method.invoke(t,args);
		}
		return invoke;
	}

	public static void main(String[] args) {
		Man man = new Man();
		MyInvocation<Man> invocation = new MyInvocation<>(man);
		IMan o = (IMan) Proxy.newProxyInstance(MyInvocation.class.getClassLoader(), man.getClass().getInterfaces(), invocation);
		Integer fun = o.fun();
		System.out.println(fun);
	}
}
