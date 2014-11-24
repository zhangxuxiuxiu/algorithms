package cn.sspku.zx.aad.daily;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand",
 * "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * @author zhangxu
 * 
 */
public class WordBreakII {
	public List<String> wordBreak(String s, Set<String> dict) {
		if (null == s || s.length() == 0 || null == dict)
			return null;

		return probe2(s, dict);
	}

	// TLE
//	private List<String> probe(String s, Set<String> dict) {
//		List<String> wordsContainer = new ArrayList<String>();
//		List<String> words = new ArrayList<String>();
//
//		int start = 0, end = 1;
//		Stack<Integer> cuts = new Stack<Integer>();
//		cuts.push(0);
//
//		while (true) {
//			if (end > s.length()) {
//				if (cuts.size() > 1) {
//					words.remove(words.size() - 1);
//					end = cuts.pop() + 1;
//					start = cuts.peek();
//				} else
//					return wordsContainer;
//			}
//
//			if (dict.contains(s.substring(start, end))) {
//				words.add(s.substring(start, end));
//				if (end == s.length()) {
//					wordsContainer.add(convert(words));
//					words.remove(words.size() - 1);
//				} else {
//					cuts.push(end);
//					start = end;
//				}
//			}
//			++end;
//		}
//	}

	private List<String> probe2(String s, Set<String> dict) {
		List<String> wordsContainer = new LinkedList<String>();
		LinkedList<String> words = new LinkedList<String>();

		int end = s.length(), start = end - 1;
		Stack<Integer> cuts = new Stack<Integer>();
		cuts.push(end);

		while (true) {
			if (start < 0) {
				if (cuts.size() > 1) {
					words.removeFirst();
					start = cuts.pop() - 1;
					end = cuts.peek();
				} else
					return wordsContainer;
			}

			if (dict.contains(s.substring(start, end))) {
				words.addFirst((s.substring(start, end)));
				if (start == 0) {
					wordsContainer.add(convert(words));
					words.removeFirst();
				} else {
					cuts.push(start);
					end = start;
				}
			}
			--start;
		}
	}

	private String convert(List<String> words) {
		Iterator<String> its = words.iterator();
		String sentence = its.next();
		while (its.hasNext())
			sentence += " " + its.next();
		return sentence;
	}

	public static void main(String[] args) {
		String words = "catsanddog";
		Set<String> dict = new HashSet<String>();
		dict.add("cat");// "cat", "cats", "and", "sand", "dog"
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		Utils.PrintString(new WordBreakII().wordBreak(words, dict));

	}
}
