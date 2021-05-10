package com.ice.stack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Auther: eclair
 * @Date: 2019/3/13 23:17
 * @Description:
 */
public class Stack<Item> implements Iterable<Item> {
	private Node first;
	private int N;

	public boolean isEmpty() {
		return first == null;
	}

	private class Node {
		private Item item;
		private Node next;
		private final Integer n = N;
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}

	public void push(Item item) {
		if (Objects.isNull(first)) {
			first = new Node();
			first.item = item;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
		}
		N++;
	}

	public Item pop() {

		if (Objects.isNull(first)) {
			return null;
		}
		Node oldFirst = first;
		Node newFirst = oldFirst.next;
		Item item = oldFirst.item;
		oldFirst.next = null;
		first = newFirst;
		N--;
		return item;

	}

	public int size() {
		return N;
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner scanner = new Scanner(new FileInputStream("F://ok.txt"));
		while (true) {
			if (scanner.hasNext()) {
				String s = scanner.nextLine();
				System.out.println(s);
			}
			Thread.sleep(0b11001000L);
		}
	}
}
