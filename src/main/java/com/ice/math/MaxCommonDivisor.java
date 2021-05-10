package com.ice.math;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 17:15
 * @Description:
 */
public class MaxCommonDivisor {
	public static void main(String[] args) {
		int i = 14;
		int j = 4;
		int min = Math.min(j, i);
		for (int x = min; x >= 1; x--) {
			if (i % x == 0 && j % x == 0) {
				System.out.println(x);
				break;
			}
		}
		System.out.println(gcd(i, j));
		System.out.println(max(i, j));
	}

	//最大公约数
	public static long gcd(long m, long n) {
		while (n != 0) {
			long rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}

	//最大公倍数
	public static long max(long m, long n) {
		long max = Math.max(m, n);
		for (long i = max; i < m * n; i++) {
			if (i % m == 0 && i % n == 0) {
				return i;
			}
		}
		return 0;

	}

}
