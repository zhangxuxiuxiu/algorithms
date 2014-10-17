package cn.sspku.zx.aad.daily;

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
		int idx = matchIndex(s, t, sIdx, tIdx);
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

	class Match {
		int sIndex, tIndex;

		Match(int sIdx, int tIdx) {
			sIndex = sIdx;
			tIndex = tIdx;
		}
	}

	//思路不清晰
	int numMatch2(String s, String t) {
		// stack.size()-1对应t的下标，而stack的值则对应s的下标
		Stack<Match> stack = new Stack<Match>();
		stack.push(new Match(-1, -1));
		Match match = null;
		int sIdx, tIdx, counter = 0, idx = 0;
		boolean forward = true;

		while (true) {
			match = stack.peek();
			sIdx = match.sIndex;
			tIdx = match.tIndex;

			idx = matchIndex(s, t, sIdx + 1, tIdx + 1);
			if (-1 != idx) {
				// 如果匹配的是t的最后一个元素，则完成一个完成的匹配
				if (tIdx + 1 == t.length() - 1)
					++counter;
				stack.push(new Match(tIdx + 1, idx));
			} else {
				while (-1 == idx) {
					stack.pop();
					stack.push(new Match(idx, tIdx));
					idx = matchIndex(s, t, sIdx + 1, tIdx);
				}
			}

			while (-1 != (idx = matchIndex(s, t, sIdx + 1, tIdx))) {
				++counter;
			}
		}
	}

	/**
	 * 从s中下标sIdx开始，寻找与t中下标为tIdx相等的字符在s中的下标
	 * 
	 * @param s
	 * @param t
	 * @param sIdx
	 * @param tIdx
	 * @return
	 */
	int matchIndex(String s, String t, int sIdx, int tIdx) {
		if (tIdx >= t.length() || tIdx < 0 || sIdx >= s.length() || sIdx < 0)
			return -1;

		while (sIdx < s.length() && s.charAt(sIdx) != t.charAt(tIdx))
			++sIdx;

		return sIdx == s.length() ? -1 : sIdx;
	}

	// Time Limit Exceeding
	public static void main(String[] args) {
		String s = "rabbbit", t = "rabbit";

		System.out.println("s=" + s + "  t=" + t + "  matches="
				+ new DistinctSubsequences().numDistinct(s, t));
	}
}
