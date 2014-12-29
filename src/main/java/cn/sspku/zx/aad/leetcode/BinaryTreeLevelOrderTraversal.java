package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return
 * its level order traversal as: [ [3], [9,20], [15,7] ] confused what
 * "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * @author zhangxu
 * 
 */
public class BinaryTreeLevelOrderTraversal {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private List<List<Integer>> lists = new ArrayList<List<Integer>>();

	public List<List<Integer>> levelOrder(TreeNode root) {
		if (null == root)
			return lists;

		return levelTraversal(root, 1);
	}

	private List<List<Integer>> levelTraversal(TreeNode root, int level) {
		if (null == root)
			return lists;
		
		if (lists.size() == level - 1)
			lists.add(new ArrayList<Integer>());
		
		lists.get(level - 1).add(root.val);
		levelTraversal(root.left,level+1);
		levelTraversal(root.right,level+1);
		
		return lists;
	}
}
