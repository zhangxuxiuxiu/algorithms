package cn.sspku.zx.aad.daily;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author zhangxu
 * 
 */
public class PreInOrderTraversal {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length==0) return null;
		if(preorder.length==1) return new TreeNode(preorder[0]);
		
		int x=preorder[0],idx=0;
		while(idx<inorder.length)
		{
			if(inorder[idx]==x) break;
			++idx;
		}
		
		int[] pre1=new int[idx],in1=new int[idx],pre2=new int[preorder.length-idx-1],in2=new int[preorder.length-idx-1];
		for(int i=1;i<idx+1;++i)
		{
			pre1[i-1]=preorder[i];
		}
		for(int i=idx+1;i<preorder.length;++i)
		{
			pre2[i-idx-1]=preorder[i];
		}
		for(int i=0;i<idx;++i)
		{
			in1[i]=inorder[i];
		}
		for(int i=idx+1;i<inorder.length;++i)
		{
			in2[i-idx-1]=inorder[i];
		}
		
		TreeNode tn=new TreeNode(x);
		tn.left=buildTree(pre1,in1);
		tn.right=buildTree(pre2,in2);
		
		return tn;
	}
}
