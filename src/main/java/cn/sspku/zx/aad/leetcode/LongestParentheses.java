package cn.sspku.zx.aad.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is
 * "()()", which has length = 4.
 * 
 * @author zhangxu
 * 
 */
public class LongestParentheses {

	public int longestValidParentheses(String s) {
		if (null == s || s.length() == 0)
			return 0;

		Stack<Character> stack = new Stack<Character>();
		int idx = 0, preIdx = -1, possibleIncr = 0;// preSize=0,
		Map<Integer, Integer> counters = new HashMap<Integer, Integer>();
		char c;
		while (idx < s.length()) {
			// 获取当前字符
			c = s.charAt(idx++);

			// 如果是左括号，直接压栈
			if (c == '(') {
				stack.push(c);
				// 只有连续的右括号才有可能增加
				possibleIncr = 0;
				// 如果是右括号,且栈顶是左括号，则累加该栈大小位置对于那个的合法括号长度，
			} else if (stack.size() != 0 && stack.pop() == '(') {
				int num = 2;
				// 如果前面刚刚刚消了一个右括号的话，那么当前可以与前面共同构成一个合法括号列表空间
				if (idx - preIdx == 1) {
					num += possibleIncr;
					possibleIncr += 2;
				}
				// else if(idx - preIdx == 2&&stack.size()==preSize)
				// {
				//
				// }
				// 如果不构成一个合法括号列表区间
				else {
					possibleIncr = 2;
				}

				// 记录这一次消除括号时的下标
				preIdx = idx;

				if (counters.containsKey(stack.size()))
					num += counters.get(stack.size());
				counters.put(stack.size(), num);
			}
		}

		// 计算最大值
		Iterator<Integer> it = counters.values().iterator();
		int max = 0, val;
		while (it.hasNext()) {
			val = it.next();
			if (val > max)
				max = val;
		}

		return max;
	}

	public int longestValidParentheses2(String s) {
		if (null == s || s.length() == 0)
			return 0;

		Stack<Character> stack = new Stack<Character>();
		int idx = 0, already = 0;
		char c;
		Map<Integer, Integer> counters = new HashMap<Integer, Integer>(), alreadyMap = new HashMap<Integer, Integer>();

		while (idx < s.length()) {
			// 获取当前字符
			c = s.charAt(idx++);

			// 如果是右括号,且栈顶是左括号，则计算该栈大小位置对于那个的合法括号长度，
			if (c == ')' && stack.size() != 0 && stack.peek() == '(') {
				stack.pop();
				already += 2;
				if (alreadyMap.containsKey(stack.size()))
					counters.put(stack.size(),
							idx - stack.size() - alreadyMap.get(stack.size()));
				else
					counters.put(stack.size(), idx - stack.size());
			}
			// 否则，记录当前堆栈大小状态时前面以及消去的括号数
			else {
				stack.push(c);
				alreadyMap.put(stack.size(), already);
			}
		}

		// 计算最大值
		Iterator<Integer> it = counters.values().iterator();
		int max = 0, val;
		while (it.hasNext()) {
			val = it.next();
			if (val > max)
				max = val;
		}

		return max;
	}

	public static void main(String[] args) {
		String[] s = new String[] { "(", "(()", "()(()()", "(()(((()",
				"(()(())", "(((())))", "(()())", "()))()())",
				"(()()()()())))(()())(()()()))" };
		LongestParentheses lp = new LongestParentheses();
		for (int idx = 0; idx < s.length; ++idx)
			System.out.println(lp.longestValidParentheses2(s[idx]));
	}
}
