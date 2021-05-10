package com.ice.sort;

import java.util.*;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 16:57
 * @Description:
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] array = new int[]{12, 3, 4, 61, 2, 6, 4, 63, 100};
		int min = 0;
		int max = array.length - 1;
		quickSort(array, min, max);
		System.out.println(Arrays.toString(array));

	}

	public static void quickSort(int array[], int left, int right) {
		if (left >= right) return;
		int target = array[left];
		int i = left;
		int j = right;
		while (i < j) {
			while (target <= array[j] && i < j) {
				j--;
			}
			while (target >= array[i] && i < j) {
				i++;
			}
			if (i < j) {
				int tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
			}
		}

		array[left] = array[i];
		array[i] = target;
		quickSort(array, left, i - 1);
		quickSort(array, i + 1, right);
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

