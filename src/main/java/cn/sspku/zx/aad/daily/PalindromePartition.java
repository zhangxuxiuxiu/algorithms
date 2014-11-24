package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 * 
 * @author zhangxu
 * 
 */
public class PalindromePartition {
	public List<List<String>> partition(String s) {
		List<List<String>> partitions = new ArrayList<List<String>>();
		// 检查边界
		if (null == s || s.length() == 0) {
			partitions.add(null);
			return partitions;
		}

		int[] cuts = new int[s.length()];
		boolean[] taken = new boolean[s.length()];
		Arrays.fill(taken, false);
		// 把每一个字母当成一个回文
		for (int idx = 0; idx < s.length(); ++idx)
			cuts[idx] = idx + 1;
		partitions.add(convert(cuts, s));
		getAllPartitions(cuts, taken, s, 0, partitions);
		return partitions;
	}

	/**
	 * 
	 * @param cuts
	 *            记录字符串是怎么划分的。[i,cuts[i])表示一个子回文
	 * @param taken
	 *            taken[i]记录当前下标为i的字符是否属于一个长度大于1的子回文
	 * @param s
	 *            被进行划分的字符串
	 * @param start
	 *            开始进行分析第一个字符的下标
	 * @param partitions
	 *            记录所有划分的方案
	 */
	private void getAllPartitions(int[] cuts, boolean[] taken, String s,
			int start, List<List<String>> partitions) {
		int idx = start;
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
					partitions.add(convert(cuts, s));
					// Utils.Print(cuts);
					getAllPartitions(cuts, taken, s, idx, partitions);
					cuts[idx] = cuts[idx] - 1;
					taken[cuts[idx]] = false;
					// System.out.print("Backtracking:");
					// Utils.Print(cuts);
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
				// Utils.Print(cuts);
				partitions.add(convert(cuts, s));
				getAllPartitions(cuts, taken, s, Math.max(0, (idx - 1)),
						partitions);
				cuts[idx - 1] = pre;
				taken[idx - 1] = false;
				taken[cuts[idx]] = false;
			}

			idx = cuts[idx];
		}
	}

	/**
	 * 将当前的划分方案转化为一个字符串列表
	 * 
	 * @param cuts
	 * @param s
	 * @return
	 */
	private List<String> convert(int[] cuts, String s) {
		List<String> ret = new ArrayList<String>();

		int idx = 0;
		while (idx < cuts.length) {
			ret.add(s.substring(idx, cuts[idx]));
			idx = cuts[idx];
		}

		return ret;
	}

	public static void main(String[] args) {
		PalindromePartition pp = new PalindromePartition();
		Utils.PrintListString(pp.partition("ababcbaba"));
	}
}
