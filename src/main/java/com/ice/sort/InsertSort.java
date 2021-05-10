package com.ice.sort;

import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 15:57
 * @Description:
 */
public class InsertSort {
	public static void main(String[] args) {
		int array[] = {12, 3, 5, 34, 65, 23, 11};
//		for (int i = 1; i < array.length; i++) {
//			int current = array[i];
//			int j = i - 1;
//			while (j >= 0 && current < array[j]) {
//				array[j + 1] = array[j];
//				j--;
//			}
//			array[j + 1] = current;
//
//		}
		insertSort(array);
		System.out.println(Arrays.toString(array));
	}

	static void insertSort(int array[]) {
		for (int i = 1; i < array.length; i++) {
			int current = array[i];
			int j = i - 1;
			while (j >= 0 && current < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = current;
		}
	}
}
