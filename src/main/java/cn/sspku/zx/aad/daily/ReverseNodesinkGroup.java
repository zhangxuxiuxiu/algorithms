package cn.sspku.zx.aad.daily;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example, Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * @author zhangxu
 * 
 */
public class ReverseNodesinkGroup {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (k < 0 || null == head)
			return null;
		if (k < 2)
			return head;

		ListNode p = head, q, _p, _q, tpr, header = null, tail = null;
		int counter;
		while (true) {
			// 找出k个节点
			counter = 0;
			// 使用q来记录临时头结点
			q = p;
			while (p != null && counter < k) {
				p = p.next;
				++counter;
			}
			// 如果没有K个节点，直接处理剩余节点
			if (counter < k) {
				if (null == header)
					return head;
				tail.next = q;
				return header;
			}

			// 反转以q为头结点，在p之前结束的子链表
			// 使用_q来记录新的临时头结点,_p来记录临时尾节点
			_q = q;
			_p = q;
			q = q.next;
			while (q != p) {
				tpr = q.next;
				q.next = _q;
				_q = q;
				q = tpr;
			}

			if (null == header) {
				header = _q;
				tail = _p;
			} else {
				tail.next = _q;
				tail = _p;
			}
		}
	}

	static void print(ListNode p) {
		while (null != p) {
			System.out.println(p.val);
			p = p.next;
		}
	}

	public static void main(String[] args) {
		ListNode root = new ListNode(1);
		/*
		 * root.next=new ListNode(2); root.next.next=new ListNode(3);
		 * root.next.next.next=new ListNode(4); root.next.next.next.next=new
		 * ListNode(5);
		 */

		print(new ReverseNodesinkGroup().reverseKGroup(root, 2));
	}
}
