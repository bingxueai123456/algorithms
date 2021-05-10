package com.review;

import java.util.Scanner;

/**
 * @Auther: eclair
 * @Date: 2019/9/1 22:10
 * @Description:
 */
public class AgeTset {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
	}

	public static int age(int n) {
		if (n == 1) return 10;
		return age(n - 1) + 2;
	}

}
