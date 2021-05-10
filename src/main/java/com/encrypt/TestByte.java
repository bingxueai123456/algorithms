package com.encrypt;

/**
 * @Auther: eclair
 * @Date: 2020/10/13 22:25
 * @Description:
 */
public class TestByte {
	public static void main(String[] args) {
		String str = "我";
		byte[] bytes = str.getBytes();
		for (byte aByte : bytes) {
			int a = aByte;
			System.out.println(a);
			String s = Integer.toBinaryString(aByte);
			System.out.println(s);
		}
	}
}
