package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return 1 since the palindrome partitioning
 * ["aa","b"] could be produced using 1 cut.
 * 
 * @author zhangxu
 * 
 */
public class PalindromePartitionII {
	int minCuts = Integer.MAX_VALUE;

	public int minCut(String s) {
		return minPartition(s);
	}

	public int minCut2(String s) {
		int n = s.length();
		int[] cut = new int[n + 1]; // number of cuts for the first k characters
		for (int i = 0; i <= n; i++)
			cut[i] = i - 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; i - j >= 0 && i + j < n
					&& s.charAt(i - j) == s.charAt(i + j); j++)
				// odd length palindrome
				cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j]);

			for (int j = 1; i - j + 1 >= 0 && i + j < n
					&& s.charAt(i - j + 1) == s.charAt(i + j); j++)
				// even length palindrome
				cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j + 1]);
		}
		return cut[n];
	}

	// TLE:ababababababababababababcbabababababababababababa
	public int minPartition(String s) {
		// 检查边界
		if (null == s || s.length() == 0)
			return 0;

		int[] cuts = new int[s.length()];
		boolean[] taken = new boolean[s.length()];
		Arrays.fill(taken, false);
		// 把每一个字母当成一个回文
		for (int idx = 0; idx < s.length(); ++idx)
			cuts[idx] = idx + 1;
		minCuts = countCuts(cuts);
		getMinCuts(cuts, taken, s, 0);
		return minCuts;
	}

	private void getMinCuts(int[] cuts, boolean[] taken, String s, int start) {
		int idx = start;
		boolean noNewPartition = true;
		while (idx < cuts.length) {
			// 如果当前回文都是相同的字母，且下一个元素与它相同，则可以形成一个新的回文
			if (cuts[idx] < cuts.length && !taken[cuts[idx]]) {
				int i;
				for (i = idx; i < cuts[idx]; ++i)
					if (s.charAt(i) != s.charAt(cuts[idx]))
						break;
				if (i == cuts[idx]) {
					taken[cuts[idx]] = true;
					cuts[idx] = cuts[idx] + 1;
					noNewPartition = false;
					// minCuts = Math.min(minCuts, countCuts(cuts));
					getMinCuts(cuts, taken, s, idx);
					cuts[idx] = cuts[idx] - 1;
					taken[cuts[idx]] = false;
					// 如果当前回文左边的元素也是同一个元素，则退出，从而避免接下来的左右扩展
					if (idx > 0 && s.charAt(idx - 1) == s.charAt(idx)) {
						idx = cuts[idx];
						continue;
					}
				}
			}
			// 如果当前回文左右两边相同，则可以左右扩展，形成新的回文
			if (idx > 0 && !taken[idx - 1] && cuts[idx] < cuts.length
					&& !taken[cuts[idx]]
					&& s.charAt(idx - 1) == s.charAt(cuts[idx])) {
				taken[idx - 1] = true;
				taken[cuts[idx]] = true;
				int pre = cuts[idx - 1];
				cuts[idx - 1] = cuts[idx] + 1;
				noNewPartition = false;
				// minCuts = Math.min(minCuts, countCuts(cuts));
				getMinCuts(cuts, taken, s, (idx - 1));
				cuts[idx - 1] = pre;
				taken[idx - 1] = false;
				taken[cuts[idx]] = false;
			}

			idx = cuts[idx];
		}
		if (noNewPartition)
			minCuts = Math.min(minCuts, countCuts(cuts));
	}

	private int countCuts(int[] cuts) {
		int cut = 0, idx = 0;
		while (idx < cuts.length) {
			++cut;
			idx = cuts[idx];
		}

		return cut - 1;
	}

	public static void main(String[] args) {
		PalindromePartitionII pp = new PalindromePartitionII();
		Utils.Print("" + pp.minCut("ababcbaba"));
	}
}
