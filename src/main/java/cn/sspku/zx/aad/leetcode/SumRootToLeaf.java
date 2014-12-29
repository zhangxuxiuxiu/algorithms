package cn.sspku.zx.aad.leetcode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * @author zhangxu
 * 
 */
public class SumRootToLeaf {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int sumNumbers(TreeNode root) {
		if(null==root) return 0;
		
		return subTotal(root,0); 
	}

	private static int subTotal(TreeNode root,int parentValue)
	{
		int value=10*parentValue+root.val;
		if(null!=root.left)
		{
			if(null !=root.right)
				return subTotal(root.left,value)+subTotal(root.right,value);
			return subTotal(root.left,value);
		}else if(null !=root.right)
		{
			return subTotal(root.right,value);
		}else
		{
			return value;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
