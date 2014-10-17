package cn.sspku.zx.aad.daily;

/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author zhangxu
 * 
 */
public class DuplicatesRemover {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		if(null==head) return null;
		
		ListNode p=head;
		while(null!=p.next)
		{
			if(p.val==p.next.val)
			{
				p.next=p.next.next;
			}
			else 
			{ 
				p=p.next;
			}
		}
		
		return head;
	}
}
