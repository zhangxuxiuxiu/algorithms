package cn.sspku.zx.aad.daily;

import java.util.LinkedList;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 * 1 / \ 2 2 / \ / \ 3 4 4 3 But the following is not: 1 / \ 2 2 \ \ 3 3
 * 
 * @author zhangxu
 * 
 */
public class SymmetricTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isSymmetric(TreeNode root) {
		if (null == root)
			return true;

		LinkedList<TreeNode> lefts = new LinkedList<TreeNode>(), rights = new LinkedList<TreeNode>();
		TreeNode pLeft = null, pRight = null;

		if (null != root.left && null != root.right) {
			lefts.addLast(root.left);
			rights.addLast(root.right);
		} else if(null==root.left&&null==root.right)
		    return true;
		else
			return false;

		while (lefts.size() > 0) {
			pLeft = lefts.removeFirst();
			pRight = rights.removeFirst();
			if (pLeft.val == pRight.val) {
				if (null != pLeft.left && null != pRight.right) {
					lefts.addLast(pLeft.left);
					rights.addLast(pRight.right);
				}

				if (null != pLeft.right && null != pRight.left) {
					lefts.addLast(pLeft.right);
					rights.addLast(pRight.left);
				}

				if (null != pLeft.left && null == pRight.right
						|| null == pLeft.right && null != pRight.left)
					return false;
			} else {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] argv) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);

		System.out.println(new SymmetricTree().isSymmetric(root));
	}
}
