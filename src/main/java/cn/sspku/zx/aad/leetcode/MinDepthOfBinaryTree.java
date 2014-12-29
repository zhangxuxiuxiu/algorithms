package cn.sspku.zx.aad.leetcode;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * @author zhangxu
 * 
 */
public class MinDepthOfBinaryTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int minDepth(TreeNode root) {
		if (null == root)
			return 0;

		if (null == root.left) {
			if (null == root.right)
				return 1;
			else
				return 1 + minDepth(root.right);
		} else {
			if (null == root.right) {
				return minDepth(root.left)+1;
			} else {
				return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
			}
		}
	}

}
