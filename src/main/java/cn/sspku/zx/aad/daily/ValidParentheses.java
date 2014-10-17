package cn.sspku.zx.aad.daily;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * 
 * @author zhangxu
 * 
 */
public class ValidParentheses {
	public boolean isValid(String s) {
		if (null == s || s.length() % 2 == 1)
			return false;

		Stack<Character> stack = new Stack<Character>();
		int idx = 0;
		char c;

		while (idx < s.length()) {
			c = s.charAt(idx);
			// 如果是左括号，则压入栈
			if (c == '(' || c == '[' || c == '{')
				stack.push(c);
			// 如果是右括号，则查看是否匹配
			else {
				if (stack.size() == 0)
					return false;

				if (c == ')') {
					if (stack.pop() != '(')
						return false;
				} else if (c == ']') {
					if (stack.pop() != '[')
						return false;
				} else if (c == '}') {
					if (stack.pop() != '{')
						return false;
				}
			}

			++idx;
		}

		return stack.size() == 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
