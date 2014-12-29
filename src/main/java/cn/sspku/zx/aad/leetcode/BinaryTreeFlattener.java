package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example, Given
 * 
 * 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like: 1 \ 2 \ 3 \ 4 \ 5
 * \ 6
 * 
 * @author zhangxu
 * 
 */
public class BinaryTreeFlattener {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public void flatten(TreeNode root) {
		if (null == root)
			return;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<TreeNode> container = new ArrayList<TreeNode>();

		stack.push(root);
		TreeNode p, pre = null;
		boolean bln;
		while (stack.size() > 0) {
			// 获取当前的节点
			p = stack.pop();
			bln = true;
			while (bln) {
				if (null != p.right) {
					// 如果前一个访问不是右节点
					if (pre != p.right) {
						container.add(p);

						if (null != p.left) {
							container.add(p.left);
							stack.push(p.right);
							p = p.left;							
						} else {
							p = p.right;
						}
					}
					else
					{
						bln=false;
					}
				} else {
					// 只有左节点
					if (null != p.left) {
						if (pre != p.left) {
							container.add(p);
							p=p.left;
						}
						else
						{
							bln=false;
						}
					} else {
						container.add(p);
						bln=false;
					}
				}
			}
			// 记录访问的节点
			pre = p;
		}

		for (int idx = 1; idx < container.size(); ++idx) {
			container.get(idx - 1).right = container.get(idx);
			container.get(idx-1).left=null;
		}
	}

	private static void print(TreeNode p) {
		while (null != p) {
			System.out.println(p.val);
			p = p.right;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);

		new BinaryTreeFlattener().flatten(root);
		print(root);
	}
}
