package cn.sspku.zx.aad.InterviewQs;

/**
 * Using Morris Traversal, we can traverse the tree without using stack and
 * recursion. The idea of Morris Traversal is based on Threaded Binary Tree. In
 * this traversal, we first create links to Inorder successor and print the data
 * using these links, and finally revert the changes to restore original tree.
 * 
 * @author zhangxu
 * 
 */
public class InOrderTraversalWithoutStack {
	private static class TreeNode {
		int val;
		TreeNode left, right;

		// public TreeNode(int k) {
		// val = k;
		// left = right = null;
		// }

	}

	/**
	 * 1. Initialize current as root 2. While current is not null If current
	 * does not have left child a) Print current’s data b) Go to the right,i.e.,
	 * current = current.right Else a) Make current as right child of the
	 * rightmost node in current's left subtree b) Go to this left child, i.e.,
	 * current = current.left
	 * 
	 * @param root
	 */
	void MorrisTraversal(TreeNode root) {
		if (null == root)
			return;

		TreeNode current = root, pre;
		while (current != null) {
			if (current.left == null)
				current = current.right;
			else {
				pre = current.left;
				while (pre.right != null && pre.right != current)
					pre = pre.right;

				/* Make current as right child of its inorder predecessor */
				if (pre.right == null) {
					pre.right = current;
					current = current.left;
				}

				/*
				 * Revert the changes made in if part to restore the original
				 * tree i.e., fix the right child of predecssor
				 */
				else {
					pre.right = null;// restore the original tree
					System.out.printf(" %d ", current.val);
					current = current.right;// traverse the right then
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
