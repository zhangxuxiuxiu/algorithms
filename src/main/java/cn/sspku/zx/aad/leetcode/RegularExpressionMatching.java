package cn.sspku.zx.aad.leetcode;

import java.util.Stack;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding
 * element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false isMatch("aa", "a*") → true isMatch("aa", ".*") →
 * true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
 * 
 * @author zhangxu
 * 
 */
public class RegularExpressionMatching {
	private static char anyOne = '.', anySeq = '*';

	public boolean isMatch(String s, String p) {
		if (null == s || null == p)
			return false;

		Stack<Integer> starIdxStack = new Stack<Integer>(), sMatchStack = new Stack<Integer>();
		int si = s.length() - 1, pi = p.length() - 1, sMatch = -1, starIdx = -1;
		while (si > -1) {
			// 如果可以确定性的匹配(p[pi]为anyOne或者两个为相等的字符)，则直接往后匹配
			if (pi > -1
					&& (p.charAt(pi) == anyOne || s.charAt(si) == p.charAt(pi))) {
				--si;
				--pi;
			} else
			// 如果不确定性的匹配，则记录当前不确定的位置之后尝试性的往后匹配
			if (pi > 0 && p.charAt(pi) == anySeq) {
				if (p.charAt(pi - 1) == s.charAt(si)
						|| p.charAt(pi - 1) == anyOne) {
					if (-1 != starIdx) {
						starIdxStack.push(starIdx);
						sMatchStack.push(sMatch);
					}
					starIdx = pi;// 标记anySeq出现的位置
					sMatch = si;// 标记在S中确认匹配成功的地方,并且匹配零个单词
				}
				pi -= 2;
			} else
			// 如果匹配错误则回到之前不确定的地方并在s处往后尝试
			if (-1 != starIdx) {
				if (p.charAt(starIdx - 1) == s.charAt(sMatch)
						|| p.charAt(starIdx - 1) == anyOne) {
					si = --sMatch;// 在之前*匹配的字母基础上再次进行匹配
					pi = starIdx - 2;// 依旧是对*进行匹配
				} else if (sMatchStack.size() > 0) {
					while (!(p.charAt(starIdx - 1) == s.charAt(sMatch) || p
							.charAt(starIdx - 1) == anyOne)
							&& sMatchStack.size() > 0) {
						sMatch = sMatchStack.pop();
						starIdx = starIdxStack.pop();
					}
					if (!(p.charAt(starIdx - 1) == s.charAt(sMatch) || p
							.charAt(starIdx - 1) == anyOne))
						return false;
					// 这种情况的sMatch是否一定可以左移？？？
					si = --sMatch;// 在之前*匹配的字母基础上再次进行匹配
					pi = starIdx - 2;// 依旧是对*进行匹配
				} else
					return false;
			} else
				return false;
		}

		while (pi > -1 && p.charAt(pi) == anySeq)
			pi -= 2;
		return pi == -1;
	}

	public static void main(String[] args) {
		RegularExpressionMatching rem = new RegularExpressionMatching();
		String[] s = new String[] { "aa", "aa", "aaa", "aa", "aa", "ab", "aab",
				"cb", "ab", "cab", "abcd", "baba","bbabacccbcbbcaaab" }, p = new String[] { "a",
				"aa", "aa", "a*", ".*", ".*", "c*a*b", "c*a*b", "c*a*b",
				"c*a*b", "d*", "b*.*","a*b*a*a*c*aa*c*bc*" };
		for (int idx = 0; idx < s.length; ++idx)
			System.out.println("s=" + s[idx] + "  p=" + p[idx] + "  isMatch="
					+ rem.isMatch(s[idx], p[idx]));
	}
}
