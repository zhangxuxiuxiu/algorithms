package cn.sspku.zx.aad.leetcode;

/**
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 * 
 * 
 * For example, the following two linked lists:
 * 
 * A: a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1.
 * 
 * 
 * Notes:
 * 
 * If the two linked lists have no intersection at all, return null. The linked
 * lists must retain their original structure after the function returns. You
 * may assume there are no cycles anywhere in the entire linked structure. Your
 * code should preferably run in O(n) time and use only O(1) memory.
 * 
 * @author zhangxu
 * 
 */
public class IntersectionofTwoLinkedLists {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (null == headA || null == headB)
			return null;

		int lenA = len(headA), lenB = len(headB);
		if (lenA > lenB)
			headA = move(headA, lenA - lenB);
		else
			headB = move(headB, lenB - lenA);

		while (headA != null) {
			if (headA == headB)
				return headA;
			headA = headA.next;
			headB = headB.next;
		}
		return null;
	}

	private ListNode move(ListNode head, int steps) {
		while (steps-- > 0)
			head = head.next;
		return head;
	}

	int len(ListNode ln) {
		int len = 0;
		while (null != ln) {
			ln = ln.next;
			++len;
		}
		return len;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
