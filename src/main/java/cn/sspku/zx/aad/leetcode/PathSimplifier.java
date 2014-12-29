package cn.sspku.zx.aad.leetcode;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 * "////"->"/"; "/."->"/";".."->"/";"/..."->"/..."
 * 
 * @author zhangxu
 * 
 */
public class PathSimplifier {

	private static char Slash = '/';
	private static String current = ".";
	private static String parent = "..";

	public String simplifyPath(String path) {
		if (null == path)
			return null;
		if (path.length() == 0)
			return "";

		String[] subPathes = path.split("/");
		Stack<String> stack = new Stack<String>();

		for (int idx = 0; idx < subPathes.length; ++idx) {
			process(stack, subPathes[idx]);
		}

		String ret = "";
		while (stack.size() > 0) {
			ret = Slash + stack.pop() + ret;
		}
		// 处理path="///","/.","/.."的情况
		if (ret.length() == 0)
			ret += Slash;

		return ret;
	}

	/**
	 * deal with each sub path
	 * 
	 * @param stack
	 * @param subPath
	 */
	private static void process(Stack<String> stack, String subPath) {
		if (subPath.length() > 0) {
			if (subPath.equals(parent))
			{
				if(stack.size() > 0)
					stack.pop();
			}
			else if (!subPath.equals(current)) {
				stack.push(subPath);
			}
		}
	}

	public static void main(String[] args) {
		String path = "/..";
		String[] strs = path.split("/");
		System.out.println("" + strs.length + " sub pathes in total");
		for (int idx = 0; idx < strs.length; ++idx) {
			System.out.println(strs[idx]);
		}

		PathSimplifier ps = new PathSimplifier();
		System.out.println("The origal string is :\"" + path
				+ "\"\nthe simplified string is :\"" + ps.simplifyPath(path)
				+ "\"");
		System.out.print("..".equals(parent));

	}

}
