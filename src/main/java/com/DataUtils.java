package com;

/**
 * @Auther: eclair
 * @Date: 2020/10/26 21:31
 * @Description:
 */
public class DataUtils {
	public static String byteToHex(byte[] array) {
		StringBuilder sb = new StringBuilder();
		for (byte b : array) {
			String s = Integer.toHexString(b & 0xff);
			if (s.length() == 1) {
				s = "0" + s;
			}
			sb.append(s);
		}
		return sb.toString();
	}
}
