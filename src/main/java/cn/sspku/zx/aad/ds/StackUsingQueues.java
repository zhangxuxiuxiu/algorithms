package cn.sspku.zx.aad.ds;

import java.util.LinkedList;
import java.util.Queue;

/**
 * using two queues to implement a stack
 * 
 * @author zhangxu
 * 
 */
public class StackUsingQueues {
	private Queue<Integer> q1, q2, q;

	public StackUsingQueues() {
		q1 = new LinkedList<Integer>();
		q2 = new LinkedList<Integer>();
	}

	public void Push(int val) {
		q2.add(val);
		while (q1.size() > 0)
			q2.add(q1.remove());
		q = q1;
		q1 = q2;
		q2 = q;
	}

	public int Pop() {
		return q1.remove();
	}
}
