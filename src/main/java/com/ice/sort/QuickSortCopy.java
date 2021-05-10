package com.ice.sort;

/**
 * @Auther: eclair
 * @Date: 2019/9/1 22:37
 * @Description:
 */
public class QuickSortCopy {
	public static void main(String[] args) {
		int[] array = new int[]{12, 3, 4, 61, 2, 6, 63, 100};
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int target = start;
		while (start < end) {
			while (start < end && array[target] < array[end]) {
				end--;
			}
			array[start] = array[end];
			while (start < end && array[target] > array[start]) {
				start++;
			}
			array[end] = array[start];
		}
		array[start] = array[target];
		quickSort(array, start + 1, end);
		quickSort(array, 0, start - 1);
	}
}
