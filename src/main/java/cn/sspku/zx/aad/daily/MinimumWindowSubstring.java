package cn.sspku.zx.aad.daily;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 * Note: If there is no such window in S that covers all characters in T, return
 * the emtpy string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.
 * 
 * @author zhangxu
 * 
 */
public class MinimumWindowSubstring {
	public String minWindow(String S, String T) {
		if (null == T || null == S || S.length() == 0 || T.length() == 0
				|| S.length() < T.length())
			return "";

		// 记录所有的字符(chars记录所有出现在T当中的字符，mets记录S当中子字符串的字符)
		Map<Character, Integer> chars = new HashMap<Character, Integer>(), mets = new HashMap<Character, Integer>();
		for (int idx = 0; idx < T.length(); ++idx)
			if (chars.containsKey(T.charAt(idx)))
				chars.put(T.charAt(idx), 1 + chars.get(T.charAt(idx)));
			else
				chars.put(T.charAt(idx), 1);

		// 找到第一个包含字串T的子串，intQueue和charQueue分别记录匹配的字符和下标
		LinkedList<Integer> intQueue = new LinkedList<Integer>();
		LinkedList<Character> charQueue = new LinkedList<Character>();
		int idx, count = 0;
		for (idx = 0; idx < S.length(); ++idx)
			if (chars.containsKey(S.charAt(idx))) {
				intQueue.addLast(idx);
				charQueue.addLast(S.charAt(idx));
				if (!mets.containsKey(S.charAt(idx))
						|| mets.get(S.charAt(idx)) < chars.get(S.charAt(idx))) {
					++count;
				}
				if (mets.containsKey(S.charAt(idx)))
					mets.put(S.charAt(idx), 1 + mets.get(S.charAt(idx)));
				else
					mets.put(S.charAt(idx), 1);
				if (count == T.length())
					break;
			}

		// 不断删除最左边的字符，在最右边补充新的字符串
		if (idx < S.length()) {
			int start = intQueue.getFirst(), end = intQueue.getLast();
			char ch = ' ';
			int p = 0;
			while (idx < S.length()) {
				System.out.println("start=" + start + "  end=" + end);
				// 删除最左边的字符使得至少一个字符的数量少于chars中字符的数量
				while (charQueue.size() > 0
						&& mets.get(charQueue.getFirst()) > chars.get(charQueue
								.getFirst())) {
					System.out.println("char=" + charQueue.getFirst()
							+ " num in mets:" + mets.get(charQueue.getFirst())
							+ " num in chars:"
							+ chars.get(charQueue.getFirst()));
					mets.put(charQueue.getFirst(),
							mets.get(charQueue.getFirst()) - 1);
					charQueue.removeFirst();
					intQueue.removeFirst();
				}
				if (charQueue.size() == 0)
					break;
				// 现在获得一个更加精简的匹配
				if (intQueue.getLast() - intQueue.getFirst() < end - start) {
					start = intQueue.getFirst();
					end = intQueue.getLast();
				}
				// 移除最开始的一个字母
				ch = charQueue.removeFirst();
				p = intQueue.removeFirst();
				// 向右寻找新的字符
				System.out.println("p=" + p);
				++idx;
				// 找到下一个前面移除的字母
				while (idx < S.length() && S.charAt(idx) != ch) {
					if (chars.containsKey(S.charAt(idx))) {
						mets.put(S.charAt(idx), mets.get(S.charAt(idx)) + 1);
						intQueue.add(idx);
						charQueue.add(S.charAt(idx));
					}
					++idx;
				}
				// 如果没有找到下一个可以移除的字母
				if (idx == S.length()) {
					// 处理删除了前面的字母却没有找到新的补充字母的情况
					if (intQueue.getLast() - p < end - start) {
						start = p;
						end = intQueue.getLast();
					}
					break;
				}
				System.out.println("idx=" + idx);
				// 找到了补充的字母
				intQueue.addLast(idx);
				charQueue.addLast(ch);
				mets.put(ch, chars.get(ch));// 恢复ch的数量
				// 更新字串的左右边界
				if (intQueue.getLast() - intQueue.getFirst() < end - start) {
					start = intQueue.getFirst();
					end = intQueue.getLast();
				}
			}

			return S.substring(start, end + 1);
		} else
			return "";
	}

	public static void main(String[] args) {
		String S = "abcabdebac", T = "cda";
		// S="bba",T="ab";
		Utils.Print(new MinimumWindowSubstring().minWindow(S, T));// 不一定需要按照ABC的次序，BCA的次序都是可以的
	}
}
