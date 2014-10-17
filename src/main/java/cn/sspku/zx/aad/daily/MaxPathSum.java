package cn.sspku.zx.aad.daily;

/**
 * Given a binary tree, find the maximum path sum.
 * 
 * The path may start and end at any node in the tree.
 * 
 * For example: Given the below binary tree,
 * 
 * 1 / \ 2 3 Return 6.
 * 
 * @author zhangxu
 * 
 */
public class MaxPathSum {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		if (null == root)
			return 0;

		nodeMaxSum(root);

		return maxSum;
	}

	private int nodeMaxSum(TreeNode root) {
		if (null == root)
			return 0;

		int leftMax = nodeMaxSum(root.left), rightMax = nodeMaxSum(root.right);
		int value = (leftMax > 0 ? leftMax : 0) + root.val
				+ (rightMax > 0 ? rightMax : 0);

		System.out.println(value + " " + leftMax + " " + rightMax + " "
				+ root.val);
		if (value > maxSum)
			maxSum = value;
		
		if (leftMax > rightMax)
			return (leftMax>0?leftMax:0) + root.val;
		return (rightMax>0?rightMax:0) + root.val;
	}

	public static void main(String[] args) {
		System.out.println(new MaxPathSum().maxPathSum(new TreeNode(1)));
	}

}
