package cn.sspku.zx.aad.leetcode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * @author zhangxu
 * 
 */
public class LinkedListCycle {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public boolean hasCycle(ListNode head) {
		if(null==head) return false;
		
		head.val=Integer.MIN_VALUE;
		while(null!=head.next)
		{
			head=head.next;
			if(head.val==Integer.MIN_VALUE) return true;
			else head.val=Integer.MIN_VALUE;
		}
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
