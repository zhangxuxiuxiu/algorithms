package cn.sspku.zx.aad.daily;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * 
 * @author zhangxu
 * 
 */
public class SwapNodesinPairs {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode swapPairs(ListNode head) {
		if (null == head || null == head.next)
			return head;

		ListNode header = new ListNode(-1);
		header.next = head;

		ListNode p1=header,p2=p1.next,p3=p2.next;
		while(true)
		{
			//交换p2和p3的位置
			p2.next=p3.next;
			p1.next=p3;
			p3.next=p2;
			
			//现在的位置为p1,p3,p2
			p1=p2;
			if(null==p1.next)
			{
				break;
			}
			else
			{
				p2=p1.next;
				if(null==p2.next) break;
				else
					p3=p2.next;
			}
		}
		
		return header.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
