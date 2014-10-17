package cn.sspku.zx.aad.daily;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 * 
 * @author zhangxu
 * 
 */
public class StringInterleaver {

	public boolean isInterleave(String s1, String s2, String s3) {

		if (null == s3)
			return false;
		if ((null == s1 || s1.equals("")) && null != s2)
			return s2.equals(s3);
		if ((null == s2 || s2.equals("")) && null != s1)
			return s1.equals(s3);
		if (null == s1 && null == s2)
			return s3.equals("");

		if (s1.length() + s2.length() != s3.length())
			return false;

		return match(s1, s2, s3, 0, 0, 0);
	}

	//纯递归实现的匹配方案
	/*private static boolean match(String s1, String s2, String s3, int idx1,
			int idx2, int idx3) {
		if (s1.charAt(idx1) == s3.charAt(idx3)) { // 只有s1适配
			if (s2.charAt(idx2) != s3.charAt(idx3)) {
				if (idx3 == s3.length() - 1)
					return true;
				else if (idx1 == s1.length() - 1)
					return s2.substring(idx2).equals(s3.substring(idx3 + 1));
				else
					return match(s1, s2, s3, idx1 + 1, idx2, idx3 + 1);
			} // s1和s2都适配
			else {
				if (idx1 != s1.length() - 1) { // s1和s2都没有到尽头
					if (idx2 != s2.length() - 1)
						return match(s1, s2, s3, idx1, idx2 + 1, idx3 + 1)
								|| match(s1, s2, s3, idx1 + 1, idx2, idx3 + 1);
					// s1没到尽头，s2到了尽头
					else
						return s1.substring(idx1)
								.equals(s3.substring(idx3 + 1))
								|| match(s1, s2, s3, idx1 + 1, idx2, idx3 + 1);
				} else { // s1到了尽头
					return s2.substring(idx2).equals(s3.substring(idx3 + 1))
							|| match(s1, s2, s3, idx1, idx2 + 1, idx3 + 1);
				}
			}
		} else { // 只有s2适配
			if (s2.charAt(idx2) == s3.charAt(idx3)) {
				if (idx3 == s3.length() - 1)
					return true;
				else if (idx2 == s2.length() - 1)
					return s1.substring(idx1).equals(s3.substring(idx3 + 1));
				else
					return match(s1, s2, s3, idx1, idx2 + 1, idx3 + 1);
			} else
				return false;
		}
	}
*/
	
	/**
	 * 递归配合非递归的方式实现匹配，在使用递归的情况下，尽可能提高效率
	 * @param s1
	 * @param s2
	 * @param s3
	 * @param idx1
	 * @param idx2
	 * @param idx3
	 * @return
	 */
	public static boolean match(String s1, String s2, String s3, int idx1, int idx2, int idx3) {
		int rep1, rep2, rep3, ch, ch1, ch2, ch3;
		
		while (idx3 < s3.length()) {
			if (idx1 < s1.length()) {
				if (s1.charAt(idx1) == s3.charAt(idx3)) {
					if (idx2 < s2.length()) {
						// 只有s1适配，则用s1的当前字母与s3进行匹配
						if (s2.charAt(idx2) != s3.charAt(idx3)) {
							++idx1;
							++idx3;
						}// s1和s2都适配
						else {
							//记录s3当前字母
							ch = s3.charAt(idx3);
							
							//统计三个字符串当前字母的重复次数
							rep1 = rep2 = rep3 = 0;							
							while (idx1 + rep1 < s1.length()&& s1.charAt(idx1 + rep1) == ch)
								++rep1;
							while (idx2 + rep2 < s2.length()&& s2.charAt(idx2 + rep2) == ch)
								++rep2;
							while (idx3 + rep3 < s3.length()&& s3.charAt(idx3 + rep3) == ch)
								++rep3;
							//如果s1和s2的重复次数之和小于s3当前字母重复的次数，则一定会匹配失败
							if (rep1 + rep2 < rep3)
								return false;
							
							// s1到了尽头，则优先匹配s2
							if (idx1 + rep1 == s1.length()) {
								if (rep3 >= rep2) {
									idx1 += rep3 - rep2;
									idx2 += rep2;
									idx3 += rep3;
								} else
									return false;
							} else {
								//记录s1当期字母重复结束后第一个字母
								ch1 = s1.charAt(idx1 + rep1);
								// s2到了尽头，则优先匹配s1
								if (idx2 + rep2 == s2.length()) {
									if (rep3 >= rep1) {
										idx1 += rep1;
										idx2 += rep3 - rep1;
										idx3 += rep3;
									} else
										return false;

								} else {
									//记录s2和s3当期字母重复结束后第一个字母
									ch2 = s2.charAt(idx2 + rep2);
									ch3 = s3.charAt(idx3 + rep3);

									if (ch1 == ch3) {
										if (ch2 == ch3) {
											//三个字符串当前字母重复结束后第一个字母都相同
											if (rep3 >= rep1) {
												//s1和s2的当前字母重复次数都足够与s3当前字母重复次数匹配
												if (rep3 >= rep2) {
													return match(s1, s2, s3,(idx1 + rep1 + 1),(idx2 + rep3 - rep1),(idx3 + rep3 + 1))
															|| match(s1,s2,s3,(idx1+ rep3- rep2),(idx2 + rep2+ 1),(idx3 + rep3+ 1));
												} else {
													//只有s1的当前字母重复次数足够与s3当前字母重复次数匹配
													idx1 += rep1 + 1;
													idx2 += rep3 - rep1;
													idx3 += rep3 + 1;
												}
											}
											else
											{
												//只有s2的当前字母重复次数足够与s3当前字母重复次数匹配
												if(rep3>=rep2) 
												{
													idx1 += rep3 -rep2;
													idx2 +=rep2+1;
													idx3 += rep3 + 1;													
												}
												else return false;
											}
										} else {
											//只有s1和s3当前字母结束重复后第一个字母相等，则优先s1匹配
											if (rep3 >= rep1) {
												idx1 += rep1 + 1;
												idx2 += rep3 - rep1;
												idx3 += rep3 + 1;
											} else
												return false;
										}
									} else 
										//只有s2和s3当前字母结束重复后第一个字母相等，则优先s1匹配
										if (ch2 == ch3) {
											if (rep3 >= rep2) {
												idx1 += rep3 - rep2;
												idx2 += rep2 + 1;
												idx3 += rep3 + 1;
											} else
												return false;
									} else
										return false;
								}
							}

						}
					}
					// s2 到了尽头，直接用s1的剩下部分匹配s3的剩下部分
					else {
						return s1.substring(idx1).equals(s3.substring(idx3));
					}
				} else {
					if (idx2 < s2.length()) {
						// 只有s2适配，则用s2的当前字母与s3进行匹配
						if (s2.charAt(idx2) == s3.charAt(idx3)) {
							++idx2;
							++idx3;
						} else
							return false;
					} else
						return false;
				}
			}
			// s1 已经到了尽头，直接用s2的剩下部分匹配s3的剩下部分
			else {
				return s2.substring(idx2).equals(s3.substring(idx3));
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out
				.println(new StringInterleaver()
						.isInterleave(
								"bababbbcabbcacbcbcbbaaabbabbabcccaccabcbcaaacbccab",
								"bbbcaaabcacbccaabbbaccccccbcbcabbcabaaacbaaccbcccbabac",
								"bbcbcbbaabbcaabcbccbbcabcbacabcbbbcbaaccccbcbcaaabbbcabcbcababbcaccaabcaacbccaabcacaaaacbabbccccabcbabac"));
	}
}
