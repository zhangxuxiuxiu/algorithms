package cn.sspku.zx.aad.daily;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character. '*' Matches any sequence of characters
 * (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false isMatch("aa", "*") → true isMatch("aa", "a*") →
 * true isMatch("ab", "?*") → true isMatch("aab", "c*a*b") → false
 * 
 * @author zhangxu
 * 
 */
public class WildcardMatcher {
	private static char anyOne = '?', anySeq = '*';

	public boolean isMatch(String s, String p) {
		if (null == s || null == p)
			return false;
		if (s.equals(p))
			return true;

		return match(s, p, 0, 0);
	}

	public boolean isMatch2(String str, String pattern) {
		int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;              
                s = ++match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
	}

	private static boolean match(String s, String p, int idx1, int idx2) {
		while (idx2 < p.length()) {
			// case1:当前字母为'?'
			if (p.charAt(idx2) == anyOne) {
				if (idx1 < s.length()) {
					++idx1;
					++idx2;
				} else
					return false;
			}
			// case2: 当前字母为'*'
			else if (p.charAt(idx2) == anySeq) {
				// 统计接下来连续的'?'或'*'之中'?'的个数,记录s当前指标
				int anyOnes = 0, start = idx1;
				String sub;
				while (idx2 < p.length()) {
					// 记录
					if (p.charAt(idx2) == anyOne) {
						// 记录'?'的个数
						++anyOnes;
						++idx2;
					} else if (p.charAt(idx2) == anySeq) {
						++idx2;
					} else
					// p中出现了一般字符
					{
						// 记录p中'?'或'*'结束之后第一个连续一般字符串
						int end = idx2;
						while (end < p.length() && p.charAt(end) != anyOne
								&& p.charAt(end) != anySeq) {
							++end;
						}
						sub = p.substring(idx2, end);

						// 尝试匹配s中所有的子串sub
						while (idx1 < s.length() - sub.length() + 1) {
							idx1 = subilize(s, idx1, sub);
							if (-1 == idx1)
								return false;
							if ((idx1 - start >= anyOnes)
									&& match(s, p, idx1 + sub.length(), end)) {
								return true;
							}
							++idx1;
						}
						// 遍历s的所有后续字符都没有找到合适的字母与ch匹配
						return false;
					}
				}
				// p之后都是'?'和'*'的组合
				if (s.length() - start >= anyOnes)
					return true;
				else
					return false;
			} else if (idx1 < s.length()) {
				// case3: 当前两字母相等
				if (p.charAt(idx2) == s.charAt(idx1)) {
					++idx1;
					++idx2;
				} else
					return false;
			} else
				return false;
		}

		return idx1 == s.length();
	}

	// 从s的下标start开始寻找与sub匹配的第一个字串，找到后返回匹配字串的首字母下标
	private static int subilize(String s, int start, String sub) {
		int idx = 0;
		while (start < s.length() - sub.length() + 1) {
			idx = 0;
			while (idx < sub.length()
					&& s.charAt(start + idx) == sub.charAt(idx))
				++idx;
			if (idx == sub.length())
				return start;
			++start;
		}

		return -1;
	}

	public static void main(String[] args) {
		WildcardMatcher wm = new WildcardMatcher();
		/*
		 * String s =
		 * "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
		 * , p =
		 * "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"
		 * ;
		 */
		//String s = "aabcbcc", p = "*ab*b*c*";
		String s = "aa", p = "a";

		System.out.println("The string is :" + s + "\nThe match pattern is :"
				+ p + "\nThe result is :" + wm.isMatch2(s, p));

	}
}
