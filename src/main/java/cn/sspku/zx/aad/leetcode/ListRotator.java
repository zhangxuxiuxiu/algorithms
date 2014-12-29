package cn.sspku.zx.aad.leetcode;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * For example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 * 
 * @author zhangxu
 * 
 */
public class ListRotator {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode rotateRight(ListNode head, int n) {
		if (null == head || n < 1)
			return head;
		
		int length = 0;
		ListNode tpr = head;
		while (null != tpr) {
			tpr = tpr.next;
			++length;
		}

		n %= length;
		if(0==n) return head;
		
		ListNode p = head, q = head;
		int counter = 0;
		// 将p前移k个节点
		while (null != p.next && counter < n) {
			p = p.next;
			++counter;
		}

		// 如果n等于或者大于链表的长度，则直接返回
		if (counter < n)
			return head;

		while (null != p.next) {
			p = p.next;
			q = q.next;
		}
		p.next = head;
		head = q.next;
		q.next = null;

		return head;

	}

	private static void print(ListNode p) {
		while (null != p) {
			System.out.println(p.val);
			p = p.next;
		}
	}

	public static void main(String[] argv) {
		ListNode root = new ListNode(0);
		root.next = new ListNode(1);
		root.next.next = new ListNode(2);
		root.next.next.next = null;

		ListRotator lr = new ListRotator();
		print(lr.rotateRight(root, 4));

	}
}
