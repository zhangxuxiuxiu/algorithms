package cn.sspku.zx.aad.daily;

/**
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: It is intended for this problem to be specified vaguely (ie, no given
 * input specs). You are responsible to gather all the input requirements up
 * front.
 * 
 * @author zhangxu
 * 
 */
/**
 * 首先是整数，很多人很自然的会忽略负整数;其次是考虑到上溢和下溢;字符串以空格开始怎么去处理;字符串中包含了除0-9之间的字符该怎么去处理
 * 
 * @author zhangxu
 * 
 */
public class StringToInteger {
	/**
	 * 所有可能的输入：1》正常输入，比如123； 2》带符号的输入，比如-123； 3》带e的输入-123e-10； 4》
	 * 特殊带e的输入，比如e123； 5》不可挽救的错误，比如+-2; 6》可以挽救的错误，比如12a23->12；7》存在空格的情况，比如“ 123
	 * ”； 8》越界的情况：-2147483649；9》其他进制的情况，日入-11919730356x
	 * 【Leetcode的CASES当中虽然有在字符串当中加入'q','x'等，但并没有把当做八或者十六进制】
	 * 
	 * @param str
	 * @return
	 */
	public int atoi(String str) {
		if (null == str || str.length() == 0)
			return 0;
		// 处理空字符
		str = str.trim().toLowerCase();

		double base = 0L;
		int scale = 10, sign = 1;
		char lowBoundary = '0', upperBoundary = '9';
		int idx = 0;
		// 确定该整数的符号
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			if (str.charAt(0) == '-')
				sign = -1;
			idx = 1;
		}
		// // 确定该整数的进制（LEETCODE没有考虑进制？？？）
		// char c = str.charAt(str.length() - 1);
		// if (c == 'x') {
		// scale = 16;
		// upperBoundary = 'f';
		// }
		// if (c == 'q') {
		// scale = 8;
		// upperBoundary = '7';
		// }

		// 计算字符串代表的整数
		while (idx < str.length() && str.charAt(idx) != 'e') {
			if (str.charAt(idx) >= lowBoundary
					&& str.charAt(idx) <= upperBoundary) {
				base = base * scale + (str.charAt(idx) - '0');
				// 如果出现INT范围外的数的话，便如下处理
				if (base * sign > Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
				if (base * sign < Integer.MIN_VALUE)
					return Integer.MIN_VALUE;

				++idx;
			}
			// 出现错误输入
			else
				return (int) (base * sign);

		}

		// 处理有'e'的情况
		if (idx != str.length()) {
			double _base = base;
			base = base * Math.pow(scale, atoi(str.substring(idx + 1)));
			// 处理在e之前是合法值，考虑e之后的数成为非法值的情况
			if (base * sign > Integer.MAX_VALUE
					|| base * sign < Integer.MIN_VALUE)
				return (int) (sign * _base);
		}

		return (int) (base * sign);
	}

	public static void main(String[] args) {
		String[] strs = new String[] { "123", "+123e-2", "+0", "-0e", "E2",
				"+-2", "   010", "  -0012a42", "2147483648", "-2147483648",
				"-2147483649", "      -11919730356x", "   -115579378e25",
				"        +11245577259q", "-11500733327q" };
		StringToInteger sti = new StringToInteger();
		for (int idx = 0; idx < strs.length; ++idx)
			System.out.println(sti.atoi(strs[idx]));
	}
}
