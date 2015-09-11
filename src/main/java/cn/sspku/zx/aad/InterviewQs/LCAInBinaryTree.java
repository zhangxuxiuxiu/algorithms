package cn.sspku.zx.aad.InterviewQs;

import java.util.ArrayList;

/**
 * 
 * @author zhangxu Given a binary tree (not a binary search tree) and two values
 *         say n1 and n2, write a program to find the least common ancestor.
 */
public class LCAInBinaryTree {
	private static class TreeNode {
		int val;
		TreeNode left, right;

		// public TreeNode(int k) {
		// val = k;
		// left = right = null;
		// }

	}

	/**
	 * Following is simple O(n) algorithm to find LCA of n1 and n2. 1) Find path
	 * from root to n1 and store it in a vector or array. 2) Find path from root
	 * to n2 and store it in another vector or array. 3) Traverse both paths
	 * till the values in arrays are same. Return the common element just before
	 * the mismatch.
	 * 
	 * @param root
	 * @param val1
	 * @param val2
	 * @return
	 */
	public TreeNode LCA1(TreeNode root, int val1, int val2) {
		ArrayList<TreeNode> path1 = new ArrayList<TreeNode>(), path2 = new ArrayList<TreeNode>();

		if (!findPath(root, path1, val1) || !findPath(root, path2, val2))
			return null;

		int idx = 0;
		while (idx < path1.size() && idx < path2.size()
				&& path1.get(idx).val == path2.get(idx).val)
			++idx;

		return path1.get(idx - 1);
	}

	/**
	 * find path from root to val node and store them in arraylist path
	 * 
	 * @param root
	 * @param path
	 * @param val
	 * @return
	 */
	boolean findPath(TreeNode root, ArrayList<TreeNode> path, int val) {
		if (null == root)
			return false;

		path.add(root);

		if (root.val == val)
			return true;

		if (findPath(root.left, path, val) || findPath(root.right, path, val))
			return true;

		path.remove(path.size() - 1);
		return false;
	}

	/**
	 * The idea is to traverse the tree starting from root. If any of the given
	 * keys (n1 and n2) matches with root, then root is LCA (assuming that both
	 * keys are present). If root doesn’t match with any of the keys, we recur
	 * for left and right subtree. The node which has one key present in its
	 * left subtree and the other key present in right subtree is the LCA. If
	 * both keys lie in left subtree, then left subtree has LCA also, otherwise
	 * LCA lies in right subtree.
	 * 
	 * @suppose val1 and val2 surely exist in the tree
	 * @param root
	 * @param val1
	 * @param val2
	 * @return
	 */
	public TreeNode LCA2(TreeNode root, int val1, int val2) {
		if (null == root)
			return null;
		// If either n1 or n2 matches with root's key, report
		// the presence by returning root (Note that if a key is
		// ancestor of other, then the ancestor key becomes LCA
		if (root.val == val1 || root.val == val2)
			return root;

		TreeNode left_lca = LCA2(root.left, val1, val2), right_lca = LCA2(
				root.right, val1, val2);
		// If both of the above calls return Non-NULL, then one key
		// is present in once subtree and other is present in other,
		// So this node is the LCA
		if (left_lca != null && right_lca != null)
			return root;
		// Otherwise check if left subtree or right subtree is LCA
		return null == left_lca ? right_lca : left_lca;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
