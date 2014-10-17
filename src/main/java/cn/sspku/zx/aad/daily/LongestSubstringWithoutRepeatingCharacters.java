package cn.sspku.zx.aad.daily;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1.
 * 
 * @author zhangxu
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (null == s || s.length() == 0)
			return 0;
		int start = 0, end = 1, max = 1, idx;
		while (end < s.length()) {
			while (-1 == (idx = search(s, start, end))) {
				if (idx == -2)
					break;
				++end;
			}
			
			if (end - start > max)
				max = end - start;
			start = idx + 1;
		}

		return max;
	}

	private int search(String s, int start, int end) {
		if (start < 0 || end >= s.length())
			return -2;

		int pos = start;
		while (pos < end && s.charAt(pos) != s.charAt(end))
			++pos;
		return pos == end ? -1 : pos;
	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters lstwrc=new LongestSubstringWithoutRepeatingCharacters();
		System.out.println(lstwrc.lengthOfLongestSubstring("abcabcbb"));
	}
}
