package cn.sspku.zx.aad.leetcode;

import java.util.Stack;

/**
 * Given a string S and a string T, count the number of distinct subsequences of
 * T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * @author zhangxu
 * 
 */
public class DistinctSubsequences {
	// Time Limit Exceeding
	public int numDistinct(String S, String T) {
		if (null == T || null == S || S.length() < T.length())
			return 0;

		return numMatch(S, T, 0, 0);
	}

	/**
	 * 从S中坐标为sIdx以后的部分匹配T中坐标为tIdx以后的部分，总共有多少种情况
	 * 
	 * @param s
	 * @param t
	 * @param sIdx
	 * @param tIdx
	 * @return
	 */
	int numMatch(String s, String t, int sIdx, int tIdx) {
		int counter = 0;

		// 查找S中对应的坐标
		int idx = findCharMatch(s, sIdx, t.charAt(tIdx));
		// 如果找不到则不存匹配的情况
		if (-1 == idx)
			return 0;
		// 如果匹配是T中的最后一个元素，则在S中继续匹配T中的最后一个元素，看有多少种情况
		if (tIdx == t.length() - 1) {
			sIdx = idx + 1;

			// counter += 1 + numMatch(s, t, idx + 1, tIdx);
			/* 或者如下非递归方式 */
			int nums = 0;
			while (sIdx < s.length()) {
				while (sIdx < s.length() && s.charAt(sIdx) != t.charAt(tIdx))
					++sIdx;
				if (sIdx < s.length()) {
					++sIdx;
					++nums;
				}
			}
			counter = nums + 1;
		}
		// 如果不是最后一个元素，记录下T中当前元素在S中的当前匹配的个数后，保持T中当前元素不变，继续统计S其他可能的匹配
		else {
			// 在当前匹配的基础上，S和T 继续往后匹配
			int _counter = numMatch(s, t, idx + 1, tIdx + 1);
			// 如果有匹配到
			if (0 != _counter)
				counter += _counter + numMatch(s, t, idx + 1, tIdx);
		}

		return counter;
	}

	// Time Limit Exceeding
	public int numDistinct2(String S, String T) {
		if (null == T || null == S || S.length() < T.length())
			return 0;

		int counter = 0, p = 0, idx;
		Stack<Integer> stack = new Stack<Integer>();
		while (true) {
			// 匹配完整的一次
			while (stack.size() < T.length() - 1) {
				idx = findCharMatch(S, p, T.charAt(stack.size()));
				if (idx != -1) {
					stack.push(idx);
					p = idx + 1;
				} else
					break;
			}
			// 完整匹配一次成功
			if (stack.size() == T.length() - 1)
				counter += countChars(S, p, T.charAt(T.length() - 1));

			if (stack.size() > 0) {
				p = stack.pop() + 1;
			} else
				return counter;
		}
	}

	private int countChars(String s, int p, char ch) {
		int counter = 0;
		while (p < s.length()) {
			if (s.charAt(p) == ch)
				++counter;
			++p;
		}
		return counter;
	}

	private int findCharMatch(String s, int p, char ch) {
		if (p >= s.length() || p < 0)
			return -1;
		while (p < s.length() && ch != s.charAt(p))
			++p;
		return p == s.length() ? -1 : p;
	}

	// passed
	public int numDistinct3(String s, String t) {
		if (null == t || null == s || s.length() == 0 || t.length() == 0
				|| s.length() < t.length())
			return 0;

		int[][] records = new int[s.length()][t.length()];
		// 1> sIdx<tIdx
		for (int tIdx = 0; tIdx < t.length(); ++tIdx)
			for (int sIdx = 0; sIdx < tIdx; ++sIdx)
				records[sIdx][tIdx] = 0;
		// 2> tIdx=0
		char ch = t.charAt(0);
		int counter = 0;
		for (int sIdx = 0; sIdx < s.length(); ++sIdx) {
			if (s.charAt(sIdx) == ch)
				++counter;
			records[sIdx][0] = counter;
		}
		// 3> ...
		for (int sIdx = 1; sIdx < s.length(); ++sIdx)
			for (int tIdx = 1; tIdx < t.length(); ++tIdx)
				if (s.charAt(sIdx) != t.charAt(tIdx))
					records[sIdx][tIdx] = records[sIdx - 1][tIdx];
				else {
					counter = records[sIdx - 1][tIdx - 1];
					ch = t.charAt(tIdx);
					for (int idx = sIdx - 1; idx > 0; --idx)
						if (ch == s.charAt(idx)) {
							counter += records[idx][tIdx];
							break;
						}
					records[sIdx][tIdx] = counter;
				}

		return records[s.length() - 1][t.length() - 1];
	}

	public static void main(String[] args) {
		String s = "aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe", t = "bddabdcae";
		// String s = "aabb", t = "aab";
		System.out.println("s=" + s + "  t=" + t + "  matches="
				+ new DistinctSubsequences().numDistinct3(s, t));
	}
}
