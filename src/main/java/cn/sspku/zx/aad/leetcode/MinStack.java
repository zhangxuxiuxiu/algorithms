package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * push(x) -- Push element x onto stack. pop() -- Removes the element on top of
 * the stack. top() -- Get the top element. getMin() -- Retrieve the minimum
 * element in the stack.
 * 
 * @author zhangxu
 * 
 */
public class MinStack {
	private class Tuple {
		int val;
		int before;

		Tuple(int v, int b) {
			val = v;
			before = b;
		}
	}

	ArrayList<Tuple> arr = new ArrayList<Tuple>();
	int minIndex = -1;

	public void push(int x) {
		int before = -1;
		// 如果是第一个元素，minIndex则赋值为0
		if (minIndex == -1)
			minIndex = 0;
		// before为次小元素的下标
		else if (x < arr.get(minIndex).val) {
			before = minIndex;
			minIndex = arr.size();
		}
		arr.add(new Tuple(x, before));
	}

	public void pop() {
		if (arr.size() > 0) {
			// 如果栈顶就是最小元素，则minIndex应该赋值为次小元素的下标
			if (arr.size() == minIndex + 1)
				minIndex = arr.get(minIndex).before;
			arr.remove(arr.size() - 1);
		}
	}

	public int top() {
		if (arr.size() > 0)
			return arr.get(arr.size() - 1).val;
		return -1;
	}

	public int getMin() {
		if (arr.size() > 0)
			return arr.get(minIndex).val;
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
