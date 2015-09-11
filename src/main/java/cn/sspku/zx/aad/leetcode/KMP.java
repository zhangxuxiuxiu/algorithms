package cn.sspku.zx.aad.leetcode;

import java.util.LinkedList;
import java.util.List;

public class KMP {
	public List<Integer> match(String text, String pattern) {
		if (null == text || null == pattern || text.length() < pattern.length())
			return null;

		List<Integer> matches = new LinkedList<Integer>();

		int[] pre = computePrefix(pattern);
		int q = -1;
		for (int idx = 0; idx < text.length(); ++idx) {
			while (q > -1 && text.charAt(idx) != pattern.charAt(q + 1))
				q = pre[q];
			if (pattern.charAt(q + 1) == text.charAt(idx))
				++q;
			if (q == pattern.length())
				matches.add(idx - pattern.length());
			q = pre[q]+1;
		}

		return matches;
	}

	private int[] computePrefix(String pattern) {
		int[] pre = new int[pattern.length()];
		pre[0] = -1;
		int k = -1;
		for (int q = 1; q < pattern.length(); ++q) {
			while (k > -1 && pattern.charAt(k + 1) != pattern.charAt(q))
				k = pre[k];
			if (pattern.charAt(k + 1) == pattern.charAt(q))
				++k;
			pre[q] = k;
		}

		return pre;
	}

	public static void main(String[] args) {
		KMP kmp = new KMP();
		String text = "abababaababacb", pattern = "ababacb";
		Utils.Print(kmp.computePrefix(pattern));// 0,0,1,2,3
		Utils.PrintIterableInt(kmp.match(text, pattern));
	}

}
