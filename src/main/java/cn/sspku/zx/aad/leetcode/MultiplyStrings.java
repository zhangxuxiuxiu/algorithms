package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * @author zhangxu
 * 
 */
public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		if (null == num1 || null == num2 || num1.length() == 0
				|| num2.length() == 0)
			return null;
		if (num1.equals("0") || num2.equals("0"))
			return "0";
		char[] result = new char[num1.length() + num2.length()];
		Arrays.fill(result, '0');

		for (int l1 = 0; l1 < num1.length(); ++l1) {
			int one = num1.charAt(num1.length() - 1 - l1) - '0';
			char[] tprResult = new char[num2.length() + 1];
			Arrays.fill(tprResult, '0');
			for (int l2 = 0; l2 < num2.length(); ++l2)
				add(tprResult, ("" + one
						* (num2.charAt(num2.length() - 1 - l2) - '0'))
						.toCharArray(), l2);
			add(result, tprResult, l1);
		}

		int i = 0;
		while (i < result.length && result[i++] == '0')
			result[i - 1] = ' ';

		return new String(result).trim();
	}

	private void add(char[] result, char[] tprResult, int start) {
		int plus = 0, val;
		for (int idx = 0; idx < tprResult.length; ++idx) {
			val = plus + result[result.length - start - 1 - idx] - '0'
					+ tprResult[tprResult.length - 1 - idx] - '0';
			plus = val / 10;
			val %= 10;
			result[result.length - start - 1 - idx] = (char) ('0' + val);
		}

		int i = result.length - start - 1 - tprResult.length;
		if (plus == 1) {
			while (result[i] == '9')
				result[i--] = '0';
			result[i] = (char) (result[i] + 1);
		}
	}

	public static void main(String[] args) {
		String one = "111111", two = "0";
		System.out.println(new MultiplyStrings().multiply(one, two));
	}
}
