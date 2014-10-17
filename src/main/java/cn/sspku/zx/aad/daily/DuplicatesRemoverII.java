package cn.sspku.zx.aad.daily;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3,
 * return 2->3.
 * 
 * @author zhangxu
 * 
 */
public class DuplicatesRemoverII {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (null == head)
			return null;

		ListNode p = head, preHead = new ListNode(0),pre=preHead;
		preHead.next = head;
		while (null !=p && null != p.next) {
			if (p.val == p.next.val) {
				while (null != p.next && p.val == p.next.val)
					p= p.next;				
					
				pre.next = p.next;
				p=p.next;
			} else {
				p=p.next;
				pre=pre.next;
			}
		}

		return preHead.next;	}
}
