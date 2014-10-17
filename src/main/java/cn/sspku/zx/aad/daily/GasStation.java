package cn.sspku.zx.aad.daily;

/**
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * Note: The solution is guaranteed to be unique.
 * 
 * @author zhangxu
 * 
 */
public class GasStation {

	public static class Node {
		int idx, val;
		Node next;

		public Node(int idx, int v) {
			this.idx = idx;
			val = v;
		}
	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (null == gas || null == cost || 0 == gas.length || 0 == cost.length
				|| gas.length != cost.length)
			return -1;
		int val=0;
		for(int idx=0;idx<gas.length;++idx)
		{
			val+=gas[idx]-cost[idx];
		}
		if(val<0) return -1;
		
		Node node = new Node(0, gas[0] - cost[0]), p;
		node.next = node;
		int v1;
		for (int idx = 1; idx < gas.length; ++idx) {
			v1 = gas[idx] - cost[idx];
			if (node.val >= 0 && node.val + v1 >= 0 || node.val < 0 && v1 < 0) {
				node.val += v1;
			} else {
				p = new Node(idx, v1);
				p.next = node.next;
				node.next = p;
				node = p;
			}
		}
		
		p=node;
		while(node.next!=node)
		{
			if (node.val >= 0 && node.val + node.next.val >= 0 || node.val < 0 && node.next.val < 0) {
				node.val += node.next.val;
				node.next=node.next.next;
			}
		}
		
		return node.idx;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
