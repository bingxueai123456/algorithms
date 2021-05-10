package com.design.bridge;


import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Auther: eclair
 * @Date: 2019/8/29 19:55
 * @Description:
 */
public class TestMain {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("ok");
	}

	public static int trimUTF(byte[] buf, int n) {
		int num = 0;
		boolean bC = false;
		for (int i = 0; i < n; i++) {
			if (buf[i] < 0 && !bC) {
				bC = true;
			} else {
				num++;
				bC = false;
			}
		}
		return num;
	}

	public static Integer fun(List list) {
		list.add(1);
		System.out.println(list);
		return 4;
	}
}

class D {
	public D() {
		super();
	}

	public D(String str) {
		this();
	}

	public static final void fun() {

	}

	public static final void fun(Integer i) {

	}

}

class F extends D {
	public static final void fun2() {

	}
}