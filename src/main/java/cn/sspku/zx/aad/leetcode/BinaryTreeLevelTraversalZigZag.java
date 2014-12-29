package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return
 * its zigzag level order traversal as: [ [3], [20,9], [15,7] ]
 * 
 * @author zhangxu
 * 
 */
public class BinaryTreeLevelTraversalZigZag {
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
			lists.add(new LinkedList<Integer>());

		LinkedList<Integer> ls = (LinkedList<Integer>) lists.get(level - 1);
		if (0 == level % 2)
			ls.addFirst(root.val);
		else
			ls.addLast(root.val);

		levelTraversal(root.left, level + 1);
		levelTraversal(root.right, level + 1);

		return lists;
	}
}
