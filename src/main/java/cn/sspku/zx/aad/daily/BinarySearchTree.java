package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the
	 * node's key. The right subtree of a node contains only nodes with keys
	 * greater than the node's key. Both the left and right subtrees must also
	 * be binary search trees.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if (null != root)
			return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

		return true;
	}

	/**
	 * @precondition node is not null
	 * @param node
	 * @param leftBound
	 * @param rightBound
	 * @return
	 */
	private static boolean validate(TreeNode node, int leftBound, int rightBound) {
		if (!(node.val > leftBound && node.val < rightBound))
			return false;
		if (null != node.left && !validate(node.left, leftBound, node.val))
			return false;
		if (null != node.right && !validate(node.right, node.val, rightBound))
			return false;

		return true;
	}

	/**
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * 
	 * Recover the tree without changing its structure.
	 * 
	 * Note: A solution using O(n) space is pretty straight forward. Could you
	 * devise a constant space solution?
	 * 
	 * @param root
	 */
	public void recoverTree(TreeNode root) {

	}

	/**
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1...n?
	 * 
	 * For example, Given n = 3, there are a total of 5 unique BST's.
	 * 
	 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
	 * 
	 * @param n
	 * @return
	 */
	private int[] trees;

	public int numTrees(int n) {
		if (n < 2)
			return 1;

		trees = new int[n + 1];
		trees[0] = trees[1] = 1;
		int result;
		for (int outer = 2; outer <= n; ++outer) {
			result = 0;
			for (int inner = 0; inner < outer; ++inner) {
				result += trees[inner] * trees[outer - 1 - inner];
			}
			trees[outer] = result;
		}

		return trees[n];
	}

	/*
	 * Given n, generate all structurally unique BST's (binary search trees)
	 * that store values 1...n.
	 * 
	 * For example, Given n = 3, your program should return all 5 unique BST's
	 * shown below.
	 * 
	 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
	 */
	public List<TreeNode> generateTrees(int n) {
		if (n < 1) {
			List<TreeNode> trees = new ArrayList<TreeNode>();
			trees.add(null);
			
			return trees;
		}

		return generateTrees(1, n);
	}

	List<TreeNode> generateTrees(int start, int end) {
		List<TreeNode> trees = new ArrayList<TreeNode>();

		if (start == end)
			trees.add(new TreeNode(start));
		else if (start < end) {
			for (int idx = start; idx <= end; ++idx) {
				List<TreeNode> leftSubTrees = generateTrees(start, idx - 1), rightSubTrees = generateTrees(
						idx + 1, end);

				if (leftSubTrees.size() > 0) {
					Iterator<TreeNode> leftIt = leftSubTrees.iterator();
					while (leftIt.hasNext()) {
						TreeNode leftTree = leftIt.next();

						if (rightSubTrees.size() > 0) {
							Iterator<TreeNode> rightIt = rightSubTrees
									.iterator();
							while (rightIt.hasNext()) {
								TreeNode rightTree = rightIt.next();

								TreeNode root = new TreeNode(idx);
								root.left = copy(leftTree);
								root.right = copy(rightTree);

								trees.add(root);
							}
						} else {
							TreeNode root = new TreeNode(idx);
							root.left = leftTree;
							trees.add(root);
						}
					}
				} else {
					Iterator<TreeNode> rightIt = rightSubTrees.iterator();
					while (rightIt.hasNext()) {
						TreeNode rightTree = rightIt.next();
						TreeNode root = new TreeNode(idx);
						root.right = rightTree;
						trees.add(root);
					}
				}
			}
		}

		return trees;
	}

	/**
	 * 完整的复制一棵树
	 * 
	 * @param root
	 * @return
	 */
	private static TreeNode copy(TreeNode root) {
		if (null != root) {
			TreeNode ret = new TreeNode(root.val);
			ret.left = copy(root.left);
			ret.right = copy(root.right);
			return ret;
		}
		return null;
	}

	/**
	 * Given an array where elements are sorted in ascending order, convert it
	 * to a height balanced BST.
	 * 
	 * @param num
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] num) {
		if (null == num || num.length == 0)
			return null;

		return subBST(num, 0, num.length - 1);
	}

	private TreeNode subBST(int[] num, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		TreeNode p = new TreeNode(num[mid]);
		p.left = subBST(num, start, mid - 1);
		p.right = subBST(num, mid + 1, end);
		return p;
	}

	public static void main(String[] argv) {
		System.out.println(new BinarySearchTree().numTrees(1));
	}
}
