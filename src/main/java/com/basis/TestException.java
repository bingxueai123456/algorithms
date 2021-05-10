package com.basis;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Auther: eclair
 * @Date: 2020/11/2 21:00
 * @Description:
 */
public class TestException {
	public static void main(String[] args) throws FileNotFoundException {
		testException2();
	}

	@SneakyThrows
	public static void testException() {
		int i = 1 / 0;
	}

	public static void testException2() throws FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("d://");
	}
}
