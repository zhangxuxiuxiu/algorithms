package cn.sspku.zx.aad.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author zhangxu
 * 
 */
public class WordBreak {
	// TLE
	public boolean wordBreak(String s, Set<String> dict) {
		if (null == s || s.length() == 0 || null == dict)
			return false;

		return probe(s, 0, dict);
	}

	private boolean probe(String s, int start, Set<String> dict) {
		if (start == s.length())
			return true;

		for (int len = 1; start + len <= s.length(); ++len) {
			if (dict.contains(s.substring(start, start + len))
					&& probe(s, start + len, dict))
				return true;
		}
		return false;
	}

	// TLE
	public boolean wordBreak2(String s, Set<String> dict) {
		if (null == s || s.length() == 0 || null == dict)
			return false;

		return probe(s, dict);
	}

	private boolean probe(String s, Set<String> dict) {
		int start = 0, end = 1;
		Stack<Integer> cuts = new Stack<Integer>();
		cuts.push(0);

		while (true) {
			if (end > s.length()) {
				if (cuts.size() > 1) {
					end = cuts.pop() + 1;
					start = cuts.peek();
				} else
					return false;
			}

			if (dict.contains(s.substring(start, end))) {
				if (end == s.length())
					return true;
				cuts.push(end);
				start = end;
			}
			++end;
		}
	}

	public boolean wordBreak3(String s, Set<String> dict) {
		if (null == s || s.length() == 0 || null == dict)
			return false;
		boolean[] probe = new boolean[s.length() + 1];
		Arrays.fill(probe, false);
		probe[0] = true;

		for (int start = 0; start < s.length(); ++start)
			for (int end = start + 1; end <= s.length(); ++end)
				if (probe[start] && dict.contains(s.substring(start, end)))
					probe[end] = true;

		return probe[s.length()];
	}

	public static void main(String[] args) {
		WordBreak wb = new WordBreak();
		String words = "catsanddog";
		Set<String> dict = new HashSet<String>();
		dict.add("cat");// "cat", "cats", "and", "sand", "dog"
		dict.add("cats");
		dict.add("and");
		// dict.add("sand");
		dict.add("dog");
		Utils.Print("" + wb.wordBreak3(words, dict));
	}
}
