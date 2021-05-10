package com.ice.letcode;

import java.util.HashMap;

/**
 * @Auther: eclair
 * @Date: 2019/7/1 22:34
 * @Description:
 */
public class TwoSum {
	public static void main(String[] args) {
		int array[] = { 12,2, 3, 4, 56, 7, 2};
		twoSum(array,4);
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			hashMap.put(array[i], i);
		}
		for (int i = 0; i < array.length; i++) {
			int index = 4 - array[i];
			if (null != hashMap.get(index) && hashMap.containsKey(index)) {
				System.out.println(i);
				System.out.println(hashMap.get(index));
				break;
			}
		}
		String str = "helloworld";

	}

	public static int[] twoSum(int[] nums, int target) {

//		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
//		int[] res = new int[2];
//		for (int i = 0; i < nums.length; ++i) {
//			m.put(nums[i], i);
//		}
//		for (int i = 0; i < nums.length; ++i) {
//			int t = target - nums[i];
//			if (m.containsKey(t) && m.get(t) != i) {
//				res[0] = i;
//				res[1] = m.get(t);
//				break;
//			}
//		}
//		return res;
		return null;
	}
}
