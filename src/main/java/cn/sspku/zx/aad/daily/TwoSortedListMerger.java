package cn.sspku.zx.aad.daily;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * @author zhangxu
 * 
 */
public class TwoSortedListMerger {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (null == l1)
			return l2;
		if (null == l2)
			return l1;

		ListNode head, tail, p1 = l1, p2 = l2;
		head = tail = null;
		while (null != p1 && null != p2) {
			if (p1.val < p2.val) {
				if (null == head) {
					head = tail = p1;
				} else {
					tail.next = p1;
					tail=p1;
				}
				p1 = p1.next;
			} else {
				if (null == head) {
					head = tail = p2;
				} else {
					tail.next = p2;
					tail=p2;
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
}
