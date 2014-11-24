package cn.sspku.zx.aad.daily;

/**
 * Validate if a given string is numeric.
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
 * "2e10" => true Note: It is intended for the problem statement to be
 * ambiguous. You should gather all requirements up front before implementing
 * one.
 * 
 * @author zhangxu
 * 
 */
public class ValidNumber {
	/**
	 * 正确的数，比如".3","3.","00",".3e9" 
	 * 错误的数，比如".","e9","-e9","-","2e6.5"
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {
		if (null == s)
			return false;

		s = s.trim().toLowerCase();
		if (s.length() == 0)
			return false;

		int idx = 0;
		char c = '0';
		// 处理符号
		if (s.charAt(0) == '+' || s.charAt(0) == '-') {
			// 如果正负符号后面没有数据了，那么它不是NUMERIC
			if (s.length() == 1)
				return false;
			idx = 1;
		}
		// 如果一开始有两个及以上的零存在，则数据mei有问题!!!
		// if (s.length() > idx + 1 && s.charAt(idx) == '0'
		// && s.charAt(idx + 1) == '0')
		// return false;

		// 处理小数点之前或者E之前的数据
		while (idx < s.length() && (c = s.charAt(idx)) != '.' && c != 'e') {
			if (c < '0' || c > '9')
				return false;
			++idx;
		}

		if (idx != s.length()) {
			// 处理小数点之后E之前的字符
			if (c == '.') {
				// 如果小数点之前没有数据则后面必须有;如果后面没有则前面必须有。比如"."->false,".3"->true,"3."->true
				if ((idx == 0 || s.charAt(idx - 1) == '+' || s.charAt(idx - 1) == '-')
						&& (idx == s.length() - 1 || ((s.charAt(idx + 1) < '0' || s
								.charAt(idx + 1) > '9'))))
					return false;
				++idx;
				// if(idx==s.length()) return false;
				while (idx < s.length() && (c = s.charAt(idx)) != 'e') {
					if (c < '0' || c > '9')
						return false;
					++idx;
				}
			}
			// 找到E了
			if (idx != s.length()) {
				// 如果E前面没有数据，或者有错误的数据则一定是错的，比如"e9","+e9"
				if (idx == 0 || s.charAt(idx - 1) == '+'
						|| s.charAt(idx - 1) == '-')
					return false;
				// e后面只能接整数
				++idx;
				if (idx < s.length()
						&& (s.charAt(idx) == '+' || s.charAt(idx) == '-'))
					++idx;
				if (idx == s.length())
					return false;

				while (idx < s.length() && s.charAt(idx) >= '0'
						&& s.charAt(idx) <= '9')
					++idx;
				return idx == s.length() ? true : false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		ValidNumber vn = new ValidNumber();
		String[] nums = new String[] { "abc", "0", "0.1", ".1", "2e10",
				"12.34e45.78", "00" };

		for (int idx = 0; idx < nums.length; ++idx)
			System.out.println(vn.isNumber(nums[idx]));
	}
}
