package cn.sspku.zx.aad.leetcode;

/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example, a = "11" b = "1" Return "100".
 * 
 * @author zhangxu
 * 
 */
public class AddBinary {
	public String addBinary(String a, String b) {
		if (null == a || a.length() == 0)
			return b;

		if (null == b || b.length() == 0)
			return a;

		String ret = "";
		int idx = 0, up = 0, dig, minLen = a.length(), maxLen = b.length();
		// 确保a的长度总是长于b的长度
		if (b.length() < minLen) {
			minLen = b.length();
			maxLen = a.length();

			String tpr = b;
			b = a;
			a = tpr;
		}
		// 计算共同的部分
		while (idx < minLen) {
			dig = a.charAt(minLen - idx - 1) + b.charAt(maxLen - idx - 1) - 2
					* '0' + up;
			up = dig / 2;
			dig %= 2;
			ret = dig + ret;

			++idx;
		}
		System.out.println("after common part calculation, ret= " + ret);
		// 计算剩余的部分
		if (up == 1) {
			while (idx < maxLen && b.charAt(maxLen - idx - 1) == '1') {
				ret = '0' + ret;
				++idx;
			}
			ret = '1' + ret;
			++idx;
		}

		System.out.println("after dealing with up equaling to 1, ret= " + ret);
		if (idx < maxLen)
			ret = b.substring(0, maxLen - idx) + ret;

		return ret;
	}

	public static void main(String[] args) {
		AddBinary ad = new AddBinary();
		String a = "101111", b = "10";
		System.out
				.println("a= " + a + "   b= " + b + "  " + ad.addBinary(a, b));

	}
}
