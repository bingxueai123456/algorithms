package com.kfaka;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2019/6/23 13:57
 * @Description:
 */
public class Demo {

	public static void main(String[] args) throws IOException {
		String str = "1,2,3, ,";
		System.out.println(str.split(",").length);
		System.out.println(Arrays.toString(str.split(",")));


	}

	public static int binSearch(int array[], int target) {
		int min = 0;
		int max = array.length - 1;
		while (min <= max) {
			int middle = min + (max - min) / 2;
			if (target > array[middle]) {
				min = middle + 1;
			} else if (target < array[middle]) {
				max = middle - 1;
			} else {
				return middle;
			}

		}
		return -1;
	}
}

class A {
	int i = 20;

	protected void fun() throws IOException {
	}

}

class B extends A {
	int i = 30;

	public void fun() throws IOException {
		System.out.println("ok");
	}
}