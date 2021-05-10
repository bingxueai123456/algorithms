package com.encrypt.hash;

import java.security.MessageDigest;

/**
 * @Auther: eclair
 * @Date: 2020/10/26 20:30
 * @Description:
 */
public class DigestDemo1 {
	public static void main(String[] args) throws Exception {
//		MessageDigest instance = MessageDigest.getInstance("SHA-1");
//		String str = "nihao";
//		byte[] digest = instance.digest(str.getBytes());
//		byte[] encode = Base64.getEncoder().encode(digest);
//		for (byte b : encode) {
//			System.out.print(b+" ");
//		}
//		String s = new String(encode);
//		System.out.println(s);

//		String man = "Man";
//		byte[] encode = Base64.getEncoder().encode(man.getBytes());
//		for (byte b : encode) {
//			System.out.print(b+" "+ (char)b +" ");
//		}
//		System.out.println(new String(encode));
		String aa = "aa";
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] digest = md5.digest(aa.getBytes());
		for (byte b : digest) {
			String s = Integer.toHexString(b & 0xff);
			if (s.length() == 1) {
				s = "0" + s;
			}
			System.out.print(s);
		}
	}
}
