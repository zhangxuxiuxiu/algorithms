package cn.sspku.zx.aad.leetcode;

import java.util.HashMap;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * @author zhangxu
 * 
 */
public class ListWithRandomPointerCopier {
	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};

	private HashMap<RandomListNode, RandomListNode> hasher = new HashMap<RandomListNode, RandomListNode>();

	public RandomListNode copyRandomListV2(RandomListNode head) {
		if (null == head)
			return null;

		copyAndSave(head);
		linkNodes(head);
		
		return hasher.get(head);
	}

	private void copyAndSave(RandomListNode p) {
		if (null != p)
			while (null != p) {
				RandomListNode tpr = new RandomListNode(p.label);
				hasher.put(p, tpr);
				p = p.next;
			}
	}
	
	private void linkNodes(RandomListNode p)
	{
		if(null!=p)
			while(null!=p)
			{
				hasher.get(p).next=hasher.get(p.next);
				hasher.get(p).random=hasher.get(p.random);
				
				p=p.next;
			}
	}

	public RandomListNode copyRandomList(RandomListNode head) {
		if (null == head)
			return null;

		RandomListNode p = head;
		while (null != p) {
			getCopier(p);
			p = p.next;
		}

		return hasher.get(head);
	}

	private RandomListNode getCopier(RandomListNode p) {
		if (null == p)
			return null;

		if (!hasher.containsKey(p)) {
			RandomListNode tpr = new RandomListNode(p.label);
			hasher.put(p, tpr);
			tpr.next = getCopier(p.next);
			tpr.random = getCopier(p.random);

			return tpr;
		}

		return hasher.get(p);
	}
}
