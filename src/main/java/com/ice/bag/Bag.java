package com.ice.bag;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Auther: eclair
 * @Date: 2019/3/13 23:10
 * @Description:
 */
public class Bag<Item> implements Iterable<Item> {
	public void add(Item item) {

	}

	public int size() {
		return 0;
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}


}
