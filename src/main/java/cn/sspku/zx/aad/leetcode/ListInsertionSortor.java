package cn.sspku.zx.aad.leetcode;

/*
 * Sort a linked list using insertion sort.
 */
public class ListInsertionSortor {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode insertionSortList(ListNode head) {

		if (null == head)
			return null;

		ListNode p = head, q = head, pMax = head;
		int max;
		while (null != p.next) {
			max = p.val;
			while (null != (q = q.next)) {
				if (q.val > max) {
					pMax = q;
					max = q.val;
				}
			}

			if (p != pMax) {
				int tpr = p.val;
				p.val = pMax.val;
				pMax.val = tpr;
			}
			
			q = pMax = p = p.next;
		}

		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
