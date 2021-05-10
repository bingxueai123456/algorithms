package com.ice.first;

/**
 * @Auther: eclair
 * @Date: 2019/3/5 22:41
 * @Description:
 */
public class BinaryQuery {

	public static int search(int array[], int key) { //log(N)
		if (null == array) return -1;
		int lo = 0;
		int hi = array.length - 1;

		while (hi >= lo) {
			int middle = lo + (hi - lo) / 2;

			if (array[middle] > key) {
				hi = middle - 1;
			} else if (array[middle] < key) {
				lo = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int array[] = {1, 2, 4, 5, 6, 7, 89};
		System.out.println(search(array, 6));
	}
}
