package cn.sspku.zx.aad.daily;

/**
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 * 
 * @author zhangxu
 * 
 */
public class ListPartitioner {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode partition(ListNode head, int x) {
		if(null==head) return null;
		
		ListNode h1,e1,h2,e2,p=head;
		h1=e1=h2=e2=null;
		
		while(null!=p)
		{
			if(p.val<x)
			{
				if(null==h1)
				{
					h1=e1=p;
				}
				else
				{
					e1.next=p;
					e1=e1.next;
				}
			}
			else
			{
				if(null==h2)
				{
					h2=e2=p;
				}
				else
				{
					e2.next=p;
					e2=e2.next;
				}
			}
			p=p.next;
		}
		
		if(null==h1) return h2;
		
		if(null!=e2)
		{
			e1.next=h2;
			e2.next=null;
		}		
		return h1;
	}
}
