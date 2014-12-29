package cn.sspku.zx.aad.leetcode;

/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 * 
 * @author zhangxu
 * 
 */
public class LongestPalindromicSubString {
	/**
	 * 前提：只有一个最长的字串，且S的长度不超过1000.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (null == s || s.length() == 0)
			return s;

		int left = 0, right = 1, halfLen;
		// 回文为单数个字母的情况
		for (int idx = 1; idx < s.length() - (right - left + 1) / 2; ++idx) {
			halfLen = 1;
			while (halfLen <= idx && idx + halfLen < s.length()
					&& s.charAt(idx - halfLen) == s.charAt(idx + halfLen))
				++halfLen;
			if (2 * halfLen - 1 > right - left) {
				left = idx - halfLen + 1;
				right = idx + halfLen;
			}
		}
		System.out.println("left=" + left + " right=" + right);
		// 回文为双数个字母的情况
		for (int idx = 0; idx < s.length() - (right - left + 2) / 2; ++idx) {
			halfLen = 1;
			while (halfLen <= idx + 1 && idx + halfLen < s.length()
					&& s.charAt(idx - halfLen + 1) == s.charAt(idx + halfLen))
				++halfLen;
			if (2 * (halfLen - 1) > right - left) {
				left = idx - halfLen + 2;
				right = idx + halfLen;
			}
		}
		return s.substring(left, right);
	}

	public static void main(String[] args) {
		LongestPalindromicSubString lps = new LongestPalindromicSubString();
		String s = "qqq";
		System.out.println(lps.longestPalindrome(s));

	}
}
