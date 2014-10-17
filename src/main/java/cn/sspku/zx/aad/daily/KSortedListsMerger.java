package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * @author zhangxu
 * 
 */
public class KSortedListsMerger {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode mergeKLists(List<ListNode> lists) {
		if (null == lists || lists.size() == 0)
			return null;

		List<ListNode> pointers = new ArrayList<ListNode>();
		for (ListNode node : lists) {
			if (null != node)
				pointers.add(node);
		}
		if (pointers.size() == 0)
			return null;
		if (pointers.size() == 1)
			return pointers.get(0);

		ListNode head = null, tail = null, q = null;
		int min, idx, mark = 0;
		while (pointers.size() > 0) {
			min = Integer.MAX_VALUE;
			for (idx = 0; idx < pointers.size(); ++idx)
				if (pointers.get(idx).val < min) {
					mark = idx;
					min = pointers.get(idx).val;
				}

			if (null == head)
				head = tail = pointers.get(mark);
			else {
				tail.next = pointers.get(mark);
				tail = pointers.get(mark);
			}
			
			q = pointers.get(mark).next;
			if (q != null) {
				pointers.set(mark, q);
			} else
				pointers.remove(mark);
		}

		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
