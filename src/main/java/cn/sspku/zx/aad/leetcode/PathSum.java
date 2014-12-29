package cn.sspku.zx.aad.leetcode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13
 * 4 / \ \ 7 2 1 return true, as there exist a root-to-leaf path 5->4->11->2
 * which sum is 22.
 * 
 * @author zhangxu
 * 
 */
public class PathSum {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (null == root)
			return false;
		// 如果是叶子节点，且值相等，则存在路径和
		if (root.left == root.right && root.val == sum)
			return true;

		if (hasPathSum(root.left, sum - root.val))
			return true;
		else
			return hasPathSum(root.right, sum - root.val);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
