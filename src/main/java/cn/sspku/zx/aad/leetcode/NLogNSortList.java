package cn.sspku.zx.aad.leetcode;

import java.util.LinkedList;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author zhangxu
 * 
 */
public class NLogNSortList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode sortList(ListNode head) {
		if (null == head)
			return head;

		LinkedList<ListNode> lists = new LinkedList<ListNode>();
		ListNode s, p = head, next;
		while (true) {
			s = p;
			while (null != p.next && p.val <= p.next.val) {
				p = p.next;
			}

			lists.add(s);
			if (null != p.next) {
				next = p.next;
				p.next = null;
				p = next;
			}
			else break;			
		}

		ListNode p1, p2;
		while (lists.size() > 1) {
			p1 = lists.removeFirst();
			p2 = lists.removeFirst();
			p = merge(p1, p2);
			lists.addLast(p);
		}

		return lists.getFirst();
	}

	private static ListNode merge(ListNode s1, ListNode s2) {
		if (null == s1)
			return s2;
		if (null == s2)
			return s1;

		ListNode p1 = s1, p2 = s2, head = null, tail = null;
		while (null != p1 && null != p2) {
			if (p1.val < p2.val) {
				if (null == head) {
					head = tail = p1;
				} else {
					tail.next = p1;
					tail = tail.next;
				}
				p1 = p1.next;
			} else {
				if (null == head) {
					head = tail = p2;
				} else {
					tail.next = p2;
					tail = tail.next;
				}
				p2 = p2.next;
			}
		}
		if (null == p1)
			tail.next = p2;
		else
			tail.next = p1;

		return head;
	}
	
	private static void print(ListNode root)
	{
		if(null==root) return ;
		
		while(null!=root)
		{
			System.out.println(root.val);
			root=root.next;
		}
	}
	
	public static void main(String[] argv)
	{
		ListNode root=new ListNode(5);
		root.next=new ListNode(4);
		root.next.next=new ListNode(3);
		root.next.next.next=new ListNode(3);
		root.next.next.next.next=new ListNode(1);
		
		print(new NLogNSortList().sortList(root));
	}
	
}
