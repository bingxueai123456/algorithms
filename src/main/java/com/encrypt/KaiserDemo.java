package com.encrypt;

/**
 * @Auther: eclair
 * @Date: 2020/10/13 21:36
 * @Description:
 */
public class KaiserDemo {
	public static void main(String[] args) {
		String input = "Hello World";
		char[] chars = input.toCharArray();
		int key = 3;
		String encrypt = encrypt(input,key);
		System.out.println("加密后的数据=" + encrypt);
		String decrypt = decrypt( encrypt,key);
		System.out.println("解密的数据="+ decrypt);

	}

	public static String decrypt(String encrypt,int key) {
		StringBuilder sb = new StringBuilder();
		char[] chars1 = encrypt.toCharArray();
		for (char c : chars1) {
			int cNum = c-key;
			char ec = (char) cNum;
			sb.append(ec);
		}
		return sb.toString();
	}

	public static String encrypt(String input,int key) {
		char[] chars = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char aChar : chars) {
			aChar = (char) ((int) aChar + key);
			sb.append(aChar);
		}
		return sb.toString();
	}
}
