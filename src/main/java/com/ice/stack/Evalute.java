package com.ice.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: eclair
 * @Date: 2019/3/19 22:26
 * @Description:
 */
public class Evalute {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<>();
		Stack<Integer> vals = new Stack<>();

		List<Object> list = Arrays.asList("(", 1, "+", "(", "(", 2, "+", 3, ")", "*", "(", 4, "*", 5, ")", ")", ")");

		Iterator<Object> iterator = list.iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next.equals("(")) {
			} else if (next.equals("+")) {
				ops.push(next.toString());
			} else if (next.equals("-")) {
				ops.push(next.toString());
			} else if (next.equals("*")) {
				ops.push(next.toString());
			} else if (next.equals("/")) {
				ops.push(next.toString());

			} else if (next.equals(")")) {
				String op = ops.pop();
				Integer val = vals.pop();
				if (op.equals("+")) {
					vals.push(val + vals.pop());
				} else if (op.equals("-")) {
					vals.push(val - vals.pop());
				} else if (op.equals("*")) {
					vals.push(val * vals.pop());

				} else if (op.equals("/")) {
					vals.push(val / vals.pop());
				}
			} else {
				vals.push(Integer.parseInt(next.toString()));
			}

		}
		System.out.println(vals.pop());

	}
}
