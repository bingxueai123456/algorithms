package com.review;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 14:27
 * @Description:
 */
public class IPTest {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress.getHostAddress());
		System.out.println(inetAddress.getCanonicalHostName());
		System.out.println(inetAddress.getHostName());
		System.out.println(inetAddress.getAddress());
	}
}
