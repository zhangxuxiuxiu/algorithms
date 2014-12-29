package cn.sspku.zx.aad.leetcode;

/**
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5. Note: Given n will always be valid. Try to do this in one pass.
 * 
 * @author zhangxu
 * 
 */
public class NthNodeRemover {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(null==head) return null;
		
		ListNode header=new ListNode(0),p=header,pre=header;
		header.next=head;
		int counter=0;
		while(counter<n)
		{
			p=p.next;
			++counter;
		}
		while(null!=p.next)
		{
			p=p.next;
			pre=pre.next;
		}
		pre.next=pre.next.next;
		
		return header.next;
	}
}
