package jvm.stack;

import java.util.Date;

/**
 * @Auther: eclair
 * @Date: 2020/11/8 14:05
 * @Description:
 */
public class StackFramTest {
	public static void main(String[] args) {
		System.out.println(new Date(1605105801000L -1616567296));
		System.out.println(Integer.MAX_VALUE);

	}
	public void method1() {
		System.out.println("方法1执行");
		method2();
		System.out.println("方法1执行完毕");
	}

	private int method2() {
		System.out.println("方法2执行开始");
		int i = 10;
		double m = method3();
		System.out.println("方法2执行完毕");
		return (int) (i +m);
	}

	private double method3() {
		System.out.println("方法3执行开始");
		double j = 20.0;
		System.out.println("方法3执行完毕");
		return j;
	}

}
