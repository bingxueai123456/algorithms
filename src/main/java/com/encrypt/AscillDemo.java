package com.encrypt;

import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2020/10/12 22:36
 * @Description:
 */
public class AscillDemo {
	public static void main(String[] args) {
//		char i = 'A';
//		System.out.println((int)i);
		String a = "AaZ";
		String[] split = a.split("");
		System.out.println(Arrays.toString(split));
		char[] chars = a.toCharArray();
		for (char aChar : chars) {
			int ascciiCode = aChar;
			System.out.println(ascciiCode);
		}
		System.out.println(a);
	}
}
