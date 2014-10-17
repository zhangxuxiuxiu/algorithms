package cn.sspku.zx.aad.daily;


/**
 * Given postorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author zhangxu
 * 
 */
public class InPostOrderTraversal {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length==0) return null;
		if(inorder.length==1) return new TreeNode(inorder[0]);
		
		int x=postorder[postorder.length-1],idx=0;
		while(idx<inorder.length)
		{
			if(inorder[idx]==x) break;
			++idx;
		}
		
		int[] post1=new int[idx],in1=new int[idx],post2=new int[inorder.length-idx-1],in2=new int[inorder.length-idx-1];
		for(int i=0;i<idx;++i)
		{
			post1[i]=postorder[i];
		}
		for(int i=idx;i<postorder.length-1;++i)
		{
			post2[i-idx]=postorder[i];
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
		tn.left=buildTree(in1,post1);
		tn.right=buildTree(in2,post2);
		
		return tn;
	}
}
