package com.ice.sort;

import java.util.*;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 16:57
 * @Description:
 */
public class QuickSort3 {
	public static void main(String[] args) {
		int[] array = new int[]{12, 3, 4, 61, 2, 6, 4, 63, 100};
		int min = 0;
		int max = array.length - 1;
		quickSort(array, min, max);
		System.out.println(Arrays.toString(array));

	}

	public static void quickSort(int array[], int left, int right) {
		if (left >= right) {
			return;
		}
		int base = division(array, left, right);
		quickSort(array, left, base - 1);
		quickSort(array, base + 1, right);
	}

	private static int division(int[] array, int left, int right) {
		int base = array[left];
		while (left < right) {
			// 从序列右端开始，向左遍历，直到找到小于base的数
			while (left < right && array[right] >= base)
				right--;
			// 找到了比base小的元素，将这个元素放到最左边的位置
			array[left] = array[right];

			// 从序列左端开始，向右遍历，直到找到大于base的数
			while (left < right && array[left] <= base)
				left++;
			// 找到了比base大的元素，将这个元素放到最右边的位置
			array[right] = array[left];
		}

		// 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
		// 而left位置的右侧数值应该都比left大。
		array[left] = base;
		return left;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}

