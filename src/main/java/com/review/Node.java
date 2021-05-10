package com.review;

/**
 * @Auther: eclair
 * @Date: 2019/8/31 09:50
 * @Description:
 */
public class Node {
	public int value;
	public Node left;
	public Node right;

	public void store(int value) {
		if (value < this.value) {
			if (left == null) {
				left = new Node();
				left.value = value;
			} else {
				left.store(value);
			}
		} else if (value > this.value) {
			if (right == null) {
				right = new Node();
				right.value = value;
			} else {
				right.store(value);
			}
		} else {
			this.value = value;
		}
	}

	public boolean find(int value) {
		System.out.println("happen " + this.value);
		if (value == this.value) {
			return true;
		} else if (value > this.value) {
			if (right == null) return false;
			return right.find(value);
		} else {
			if (left == null) return false;
			return left.find(value);
		}
	}

	public void preList() {
		System.out.print(this.value + ",");
		if (left != null) left.preList();
		if (right != null) right.preList();
	}

	public void middleList() {
		if (left != null) left.middleList();
		System.out.print(this.value + ",");
		if (right != null) right.middleList();
	}

	public void afterList() {
		if (left != null) left.afterList();
		if (right != null) right.afterList();
		System.out.print(this.value + ",");
	}

	public static void main(String[] args) {
		int[] data = new int[7];
		for (int i = 1; i <= 7; i++) {
			data[i - 1] = i;
		}
		System.out.println();
		Node root = new Node();
		root.value = 4;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == 4) continue;
			root.store(data[i]);
		}
		boolean b = root.find(data[3]);
		System.out.println("find:" + b);
		root.preList();
		System.out.println("========");
		root.middleList();
		System.out.println("===========");
		root.afterList();
	}

}
