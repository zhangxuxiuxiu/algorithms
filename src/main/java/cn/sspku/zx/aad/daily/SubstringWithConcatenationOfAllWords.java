package cn.sspku.zx.aad.daily;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 * 
 * @author zhangxu
 * 
 */
public class SubstringWithConcatenationOfAllWords {
	private Map<String, Integer> words = null;
	private Map<String, Integer> metWords = null;

	public List<Integer> findSubstring(String S, String[] L) {
		List<Integer> subIndexes = new LinkedList<Integer>();
		if (null == L || L.length == 0)
			return subIndexes;
		int eachLen = L[0].length();
		if (null == S || S.length() < eachLen * L.length)
			return subIndexes;

		words = new HashMap<String, Integer>();
		for (int idx = 0; idx < L.length; ++idx) {
			if (words.containsKey(L[idx]))
				words.put(L[idx], words.get(L[idx]) + 1);
			else
				words.put(L[idx], 1);
		}
		metWords = new HashMap<String, Integer>();

		int start = 0;
		while (start <= S.length() - eachLen * L.length) {
			// 如果当前起点的单词属于字典当中的词
			if (words.containsKey(S.substring(start, start + eachLen))) {
				String word = S.substring(start, start + eachLen);
				metWords.put(word, 1);
				int _start = start + eachLen, count = 1;
				while (count < L.length) {
					word = S.substring(_start, _start += eachLen);
					if (!words.containsKey(word) || metWords.containsKey(word)
							&& metWords.get(word) == words.get(word))
						break;
					if (metWords.containsKey(word))
						metWords.put(word, metWords.get(word) + 1);
					else
						metWords.put(word, 1);
					++count;
				}

				if (count == L.length)
					subIndexes.add(start);

				metWords.clear();
			}
			++start;
		}

		return subIndexes;
	}

	public static void main(String[] args) {
		// S: "barfoothefoobarman" L: ["foo", "bar"]-->0,9
		// S: "abababab" L: ["a","b"]-->0,1,2,3,4,5,6
		// S: "abababab" L: ["a","b","a"]-->0,2,4
		String s = "barfoothefoobarman";
		String[] words = new String[] { "foo", "bar" };
		SubstringWithConcatenationOfAllWords swc = new SubstringWithConcatenationOfAllWords();
		Iterator<Integer> it = swc.findSubstring(s, words).iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
