package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * @author zhangxu
 * 
 */
public class BinaryTreeTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (null == root)
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = null;
		stack.push(root);
		while (stack.size() != 0) {
			current = stack.pop();
			result.add(new Integer(current.val));

			if (null != current.right)
				stack.push(current.right);
			if (null != current.left)
				stack.push(current.left);
		}

		return result;
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (null == root)
			return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = null, lastE = null;
		stack.push(root);

		while (0 != stack.size()) {
			current = stack.peek();
			if (null != current.right) {
				// 如果前一个处理的是右子树，那就处理当前树
				if (lastE == current.right) {
					lastE = stack.pop();
					result.add(new Integer(lastE.val));
				} else// 否则将左右子树加入
				{
					stack.push(current.right);
					if (null != current.left)
						stack.push(current.left);
				}
			} else if (null != current.left) {
				// //如果前一个处理的是左子树且右子树为空，那就处理当前树
				if (lastE == current.left) {
					lastE = stack.pop();
					result.add(new Integer(lastE.val));
				} else // 否则将左子树加入
				{
					stack.push(current.left);
				}
			} else// 左右子树都为空，直接处理当前树
			{
				lastE = stack.pop();
				result.add(new Integer(lastE.val));
			}
		}

		return result;
	}

	public List<Integer> middleorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (null == root)
			return result;

		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		// 记录与nodeStack对应的节点之前是否已经访问过了
		// 一个节点是否已经被访问是指该节点是否已经经历先左到底再右一个节点
		Stack<Boolean> visitStack = new Stack<Boolean>();

		nodeStack.push(root);
		visitStack.push(false);

		TreeNode p;
		boolean visited;

		while (nodeStack.size() > 0) {
			p = nodeStack.pop();
			visited = visitStack.pop();

			if (!visited) {
				// 找到最左边的节点
				while (null != p.left) {
					nodeStack.push(p);
					visitStack.push(true);
					p = p.left;
				}
			}
			
			if (null != p.right) {
				nodeStack.push(p.right);
				visitStack.push(false);
			}
			result.add(p.val);
			System.out.println(p.val);
		}

		return result;
	}

	public static void main(String[] args) {
		TreeNode root=new TreeNode(1);
		root.right=new TreeNode(2);
		root.right.left=new TreeNode(3);
		new BinaryTreeTraversal().middleorderTraversal(root);
	}

}
