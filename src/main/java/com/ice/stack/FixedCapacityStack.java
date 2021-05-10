package com.ice.stack;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @Auther: eclair
 * @Date: 2019/3/20 22:23
 * @Description:
 */
public class FixedCapacityStack<T> implements Iterable<T> {
	T element[];
	int size;

	public FixedCapacityStack(int capacity) {
		this.element = (T[]) new Object[capacity];
	}

	public void push(T t) {
		if (size >= element.length) {
			resize(size * 2);
		}
		element[size++] = t;
	}

	public T pop() {
		T t = element[--size];
		element[size] = null;

		if (size > 0 && size == element.length / 4) {
			resize(element.length / 2);
		}

		return t;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return this.size;
	}

	private void resize(int max) {
		element = Arrays.copyOf(element, max);
	}

	public static void main(String[] args) {
		FixedCapacityStack<Integer> f = new FixedCapacityStack<>(2);
		f.push(34);
		f.push(33);
		f.push(35);
		for (Integer i : f) {
			System.out.println(i);
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<T> {
		private int i = size;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public T next() {
			return FixedCapacityStack.this.element[--i];
		}
	}
}
