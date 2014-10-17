package cn.sspku.zx.aad.daily;

import java.util.LinkedList;

/**
 * Given a binary tree
 * 
 * struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode
 * *next; } Populate each next pointer to point to its next right node. If there
 * is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space. You may assume that it is a perfect
 * binary tree (ie, all leaves are at the same level, and every parent has two
 * children). For example, Given the following perfect binary tree, 1 / \ 2 3 /
 * \ / \ 4 5 6 7 After calling your function, the tree should look like: 1 ->
 * NULL / \ 2 -> 3 -> NULL / \ / \ 4->5->6->7 -> NULL
 * 
 * @author zhangxu
 * 
 */
public class NextRightPointerPopulator {
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
		int roundNodes = 1, i = 0;
		TreeLinkNode p = null;
		while (quene.size() > 0) {
			p=null;
			
			while (i < roundNodes) {
				if (null == p) {
					p = quene.removeFirst();
				} else {
					p.next = quene.removeFirst();
					p = p.next;
				}

				if (null != p.left) {
					quene.addLast(p.left);
					quene.addLast(p.right);
				}
				System.out.println(p.val);
				++i;
			}		
			p.next = null;
				
			i = 0;
			roundNodes *= 2;
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
		
		new NextRightPointerPopulator().connect(root);
	}
	
}
