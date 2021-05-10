package com.ice.first;

import java.lang.reflect.Method;

/**
 * @Auther: eclair
 * @Date: 2019/3/5 22:57
 * @Description:
 */
public class Prime {
	public static boolean isPrime(int i) {
		if (i < 2) {
			return false;
		}

		for (int j = 2; j <= Math.sqrt(i); j++) {
			if (i % j == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NoSuchMethodException {
		Method isPrime = Prime.class.getMethod("isPrime", int.class);
		Class<?> returnType = isPrime.getReturnType();
		System.out.println(returnType);

	}
}
