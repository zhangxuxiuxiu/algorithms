package cn.sspku.zx.aad.daily;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 
 * return 1->4->3->2->5->NULL.
 * 
 * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
 * 
 * @author zhangxu
 * 
 */
public class LinkedListReverserII {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	/**
	 * 1 ≤ m ≤ n ≤ length of list
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(n==m) return head;
		
		ListNode header=new ListNode(0),pre,start,after,p=header;
		header.next=head;
		int counter=1;
		//找到第m-1个元素
		while(counter<m)
		{
			p=p.next;
			++counter;
		}
		pre=p;
		start=p.next;
		
		//找到第n个元素
		while(counter<=n)
		{
			p=p.next;
			++counter;
		}
		after=p.next;
		p.next=null;
		
		//反转[m,n]的元素链表
		ListNode s1,e1,q;
		s1=e1=null;
		p=start;
		while(p!=null)
		{
			q=p;
			p=p.next;
			if(null==s1)
			{
				s1=e1=q;
			}else
			{
				q.next=s1;
				s1=q;
			}
		}
		
		//连接这三段链表
		pre.next=s1;
		e1.next=after;
		
		return header.next;
	}
}
