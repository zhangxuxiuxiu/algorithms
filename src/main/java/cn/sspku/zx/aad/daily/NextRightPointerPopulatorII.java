package cn.sspku.zx.aad.daily;

import java.util.LinkedList;

/**
 * Populating Next Right Pointers in Each Node II Total Accepted: 15864 Total
 * Submissions: 52279 My Submissions Follow up for problem
 * "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution
 * still work?
 * 
 * Note:
 * 
 * You may only use constant extra space. For example, Given the following
 * binary tree, 1 / \ 2 3 / \ \ 4 5 7 After calling your function, the tree
 * should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ \ 4-> 5 -> 7 -> NULL
 * 
 * @author zhangxu
 * 
 */
public class NextRightPointerPopulatorII {
	public static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

	public void connect(TreeLinkNode root) {
		if (null == root)
			return;

		LinkedList<TreeLinkNode> quene = new LinkedList<TreeLinkNode>();
		quene.addLast(root);
		int roundNodes,tpr=1, i = 0;
		TreeLinkNode p;
		while (quene.size() > 0) {
			p=null;
			i = 0;	
			roundNodes = tpr;
			tpr=0;
			
			System.out.println("New Round:");
			while (i < roundNodes) {
				if (null == p) {
					p = quene.removeFirst();
				} else {
					p.next = quene.removeFirst();
					p = p.next;
				}

				if (null != p.left) {
					quene.addLast(p.left);
					++tpr;
				}
				
				if (null != p.right) {
					quene.addLast(p.right);
					++tpr;
				}
				System.out.println(p.val);
				++i;
			}		
			p.next = null;			
		}		
	}
	
	public static void main(String[] argv)
	{
		TreeLinkNode root=new TreeLinkNode(0);
		root.left=new TreeLinkNode(1);
		root.right=new TreeLinkNode(2);
		root.left.left=new TreeLinkNode(3);
		root.left.right=new TreeLinkNode(4);
		root.right.left=new TreeLinkNode(5);
		root.right.right=new TreeLinkNode(6);
		
		new NextRightPointerPopulatorII().connect(root);
	}
}
