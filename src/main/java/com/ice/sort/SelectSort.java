package com.ice.sort;

import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 15:46
 * @Description:
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] array = new int[]{12, 3, 4, 61, 2, 6, 63, 100};
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]) {
					int temp = array[min];
					array[min] = array[j];
					array[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}
}
