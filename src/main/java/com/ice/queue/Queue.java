package com.ice.queue;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Auther: eclair
 * @Date: 2019/3/13 23:14
 * @Description:
 */
public class Queue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;

	private class Node {
		Item item;
		Node next;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public void enqueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;

		if (isEmpty()) first = last;
		else {
			oldLast.next = last;
		}
		N++;
	}

	public Item dequeue() {
		if (Objects.isNull(first)) {
			return null;
		}
		Node oldFirst = first;
		Node newFirst = first.next;
		if (newFirst == null) {
			last = null;
		} else {
			first = newFirst;
			oldFirst.next = null;
		}
		N--;
		return oldFirst.item;

	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return 0;
	}

}
