package cn.sspku.zx.aad.ds;

import java.util.Stack;

/**
 * using two stacks to implement a queue
 * 
 * @author zhangxu
 * 
 */
public class QueueUsingStacks {
	private Stack<Integer> s1 = new Stack<Integer>(),
			s2 = new Stack<Integer>();

	/**
	 * In this implementation, enqueue is costly
	 * 
	 */
	public int Dequeue() {
		return s1.pop();
	}

	public void Enqueue(int val) {
		s2.push(val);
		while (s1.size() > 0)
			s2.push(s1.pop());
		while (s2.size() > 0)
			s1.push(s2.pop());
	}

	/**
	 * In this implementation,dequeue is costly, but better than version 1
	 * 
	 */
	public int Dequeue2() {
		if (s2.isEmpty())
			while (s1.size() > 0)
				s2.push(s1.pop());
		return s2.pop();
	}

	public void Enqueue2(int val) {
		s1.push(val);
	}

	/**
	 * In this implementation, only one user stack is used
	 * 
	 */
	public int Dequeue3() {
		if (s1.size() == 1)
			return s1.pop();
		int x = s1.pop(), res = Dequeue3();
		s1.push(x);
		return res;
	}

	public void Enqueue3(int val) {
		s1.push(val);
	}
}
