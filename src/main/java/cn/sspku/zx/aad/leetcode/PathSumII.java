package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13
 * 4 / \ / \ 7 2 5 1 return [ [5,4,11,2], [5,8,4,5] ]
 * 
 * @author zhangxu
 * 
 */
public class PathSumII {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> paths = new ArrayList<List<Integer>>();

		if (null != root) {
			List<Integer> proto = new ArrayList<Integer>();
			findPath(root, sum, proto, paths);
		}

		return paths;
	}

	private void findPath(TreeNode root, int sum, List<Integer> proto,
			List<List<Integer>> paths) {
		if (null == root)
			return;

		proto.add(root.val);
		if (root.left == root.right && root.val == sum) {
			List<Integer> path = new ArrayList<Integer>(Arrays.asList(new Integer[proto.size()]));
			Collections.copy(path, proto);
			paths.add(path);
			
			//回溯，消除影响
			proto.remove(proto.size() - 1);
			return;
		}

		findPath(root.left, sum - root.val, proto, paths);
		findPath(root.right, sum - root.val, proto, paths);
		//回溯，消除影响
		proto.remove(proto.size() - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
