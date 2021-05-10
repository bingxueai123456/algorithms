package com.review;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 15:54
 * @Description:
 */
public class TestInterface {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(test());
	}
	static int test() {
			int x  = 1;
		try {
			return x;
		}finally {
			++x;
			return x;
		}
	}
}

abstract class C implements A, B {
	@Override
	public void fun() {
		A.super.fun();
	}

}

interface A {
	default void fun() {
		System.out.println("ok");
	}
}

interface B {
	default void fun() {
		System.out.println("no ok");
	}
}
class D {
	private String name;
	private static  Integer age;

	class DD {
		public void fun() {
		}
	}
	static class CC {

		public static void fun() {
			age = 1;
		}
		public void fun33() {
		}
	}
}