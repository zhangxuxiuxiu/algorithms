package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes'
 * values. (ie, from left to right, level by level from leaf to root).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return
 * its bottom-up level order traversal as: [ [15,7], [9,20], [3] ]
 * 
 * @author zhangxu
 * 
 */

public class BinaryTreeLevelOrderTraversalII {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (null == root)
			return lists;

		return levelTraversal(root, 1);
	}

	private List<List<Integer>> levelTraversal(TreeNode root, int level) {
		if (null == root)
			return lists;

		if (lists.size() == level - 1)
			lists.addFirst(new ArrayList<Integer>());

		lists.get(lists.size()-level).add(root.val);
		levelTraversal(root.left, level + 1);
		levelTraversal(root.right, level + 1);

		return lists;
	}
}
