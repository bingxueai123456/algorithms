package com.ice.sort;

import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 15:36
 * @Description:
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] array = new int[]{12, 3, 4, 61, 2, 6, 63, 100, 5};
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}
}
