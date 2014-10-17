package cn.sspku.zx.aad.daily;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
 * L0→Ln→L1→Ln-1→L2→Ln-2→… You must do this in-place without altering the nodes'
 * values.
 * 
 * @author zhangxu
 * 
 */
public class RecorderList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public void reorderList(ListNode head) {
		if (null == head)
			return;

		int length = lengthOf(head);
		System.out.println("total length: " + length);

		// 找出后半部分起点
		int _start = (length+1) / 2, idx = 1;
		ListNode p1 = head;
		while (idx < _start) {
			p1 = p1.next;
			++idx;
		}
		System.out.println("last half begins at idx: " + idx);

		// 反转后半部分链表
		ListNode head2 = reverseList(p1.next);
		p1.next = null;
		Print(head);
		Print(head2);
		// 合并两条子链表成一条链表
		combine(head, head2);
		Print(head);
	}

	// 计算链表长度
	int lengthOf(ListNode head) {
		if (null == head)
			return 0;

		int length = 1;
		ListNode p1 = head;
		// 统计链表长度
		while (null != (p1 = p1.next)) {
			++length;
		}

		return length;
	}

	// 反转整条链表
	ListNode reverseList(ListNode head) {
		if (null == head)
			return null;
        
		ListNode p, q = head.next;
		head.next=null;//切断联系
		while (null != q) {
			p = q;
			q = q.next;
			p.next = head;
			head = p;
		}

		return head;
	}

	// 合并两条子链表成一条链表
	void combine(ListNode head, ListNode head2) {
		ListNode p1 = head, _p1 = head, p2 = head2, _p2 = head2;
		while (null != p2) {
			_p1 = _p1.next;
			_p2 = _p2.next;
			p1.next = p2;
			p2.next = _p1;
			p1 = _p1;
			p2 = _p2;
		}
	}

	public ListNode createList(int[] data) {
		if (null == data || data.length == 0)
			return null;

		ListNode head = new ListNode(data[0]), p = head;
		for (int idx = 1; idx < data.length; ++idx) {
			p.next = new ListNode(data[idx]);
			p = p.next;
		}

		return head;
	}

	public void Print(ListNode head) {
		while (head != null) {
			System.out.printf("%6d", head.val);
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		RecorderList rl = new RecorderList();

		ListNode head = rl.createList(new int[] {1,2,3,4,5});
		rl.Print(head);
		rl.reorderList(head);		
	}
}
