package cn.sspku.zx.aad.leetcode;

/**
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
 * 
 * @author zhangxu
 * 
 */
public class AddTwoNumbers {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (null == l1)
			return l2;
		if (null == l2)
			return l1;

		int up = 0, dig;
		ListNode p1 = l1, p2 = l2, p3 = null, ret = null;
		// 处理公共部分
		while (null != p1 && null != p2) {
			dig = p1.val + p2.val+up;
			up = dig / 10;
			dig %= 10;
			if (null == p3) {
				ret = new ListNode(dig);
				p3 = ret;
			} else {
				p3.next = new ListNode(dig);
				p3 = p3.next;
			}

			p1 = p1.next;
			p2 = p2.next;
		}

		// 处理有进位的情况
		if (up == 1) {
			//确保p1为null
			if (null == p2) {
				p2 = p1;
				p1 = null;
			}

			// 处理剩余位是9的情况
			while (null != p2 && p2.val == 9) {
				p3.next = new ListNode(0);
				p3 = p3.next;
				p2 = p2.next;
			}
			if (null != p2) {
				p3.next = new ListNode(p2.val + 1);
				p3 = p3.next;
				p2 = p2.next;
				p3.next = p2;
			} else
				p3.next = new ListNode(1);
		} else
			p3.next = null == p1 ? p2 : p1;

		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
