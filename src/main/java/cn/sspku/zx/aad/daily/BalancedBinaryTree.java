package cn.sspku.zx.aad.daily;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * @author zhangxu
 * 
 */
public class BalancedBinaryTree {

	private static int unBalanced=-1;
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isBalanced(TreeNode root) {
		if(unBalanced==height(root))
			return false;
		return true;
	}

	private int height(TreeNode root)
	{
		if(null==root) return 0;
		
		int leftH=height(root.left),rightH=height(root.right);
		if(unBalanced==leftH||unBalanced==rightH) return unBalanced;
		
		if(Math.abs(leftH-rightH)>1) return unBalanced;
		
		return Math.max(leftH, rightH)+1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
