package cn.sspku.zx.aad.leetcode;

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

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
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

	/**
	 * Given a singly linked list where elements are sorted in ascending order,
	 * convert it to a height balanced BST.
	 */
	private ListNode sortedP;

	public TreeNode sortedListToBST(ListNode head) {
		if (null == head)
			return null;

		int length = 0;
		ListNode p = head;
		while (null != p) {
			p = p.next;
			++length;
		}
		sortedP = head;
		return subBST(length);
	}

	private TreeNode subBST(int subLen) {
		if (subLen == 0)
			return null;
		if (subLen == 1) {
			int val = sortedP.val;
			sortedP = sortedP.next;
			return new TreeNode(val);
		}

		TreeNode left, mid, right;
		left = subBST(subLen / 2);
		mid = new TreeNode(sortedP.val);
		sortedP = sortedP.next;
		right = subBST(subLen - 1 - subLen / 2);
		mid.left = left;
		mid.right = right;
		return mid;
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
	TreeNode firstElement = null;
	TreeNode secondElement = null;
	// The reason for this initialization is to avoid null pointer exception in
	// the first comparison when prevElement has not been initialized
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {

		// In order traversal to find the two elements
		traverse(root);

		// Swap the values of the two nodes
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void traverse(TreeNode root) {

		if (root == null)
			return;

		traverse(root.left);

		// Start of "do some business",
		// If first element has not been found, assign it to prevElement (refer
		// to 6 in the example above)
		if (firstElement == null && prevElement.val >= root.val) {
			firstElement = prevElement;
		}

		// If first element is found, assign the second element to the root
		// (refer to 2 in the example above)
		if (firstElement != null && prevElement.val >= root.val) {
			secondElement = root;
		}
		prevElement = root;

		// End of "do some business"

		traverse(root.right);
	}

	void recoverTree2(TreeNode root) {
		if (root == null)
			return;
		TreeNode ptr, pred, current;
		TreeNode pred1, cur1, cur2;// pred2,;

		current = root;
		ptr = pred = null;
		pred1 = cur1 = cur2 = null;// pred2 =

		while (current != null) {
			if (current.left == null) {
				pred = current;
				current = current.right;
			} else {
				ptr = current.left;
				while (ptr.right != null && ptr.right != current)
					ptr = ptr.right;
				if (ptr.right == null) {
					ptr.right = current;
					current = current.left;
				} else {
					ptr.right = null;
					pred = current;
					current = current.right;
				}
			}
			if (pred != null && current != null && pred.val > current.val) {
				if (pred1 == null) {
					pred1 = pred;
					cur1 = current;
				} else {
					// pred2 = pred;
					cur2 = current;
				}
			}
		}

		int tmp;
		if (pred1 != null && cur2 != null) {
			tmp = pred1.val;
			pred1.val = cur2.val;
			cur2.val = tmp;
		} else {
			tmp = pred1.val;
			pred1.val = cur1.val;
			cur1.val = tmp;
		}
	}

	public static void main(String[] argv) {

		System.out.println(new BinarySearchTree().numTrees(1));
	}
}
