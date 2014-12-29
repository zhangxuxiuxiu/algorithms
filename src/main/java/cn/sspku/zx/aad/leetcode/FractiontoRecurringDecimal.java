package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * For example,
 * 
 * Given numerator = 1, denominator = 2, return "0.5". Given numerator = 2,
 * denominator = 1, return "2". Given numerator = 2, denominator = 3, return
 * "0.(6)".
 * 
 * @author zhangxu
 * 
 */
public class FractiontoRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		String retStr = "";
		if (numerator < 0 && denominator > 0 || numerator > 0
				&& denominator < 0)
			retStr += "-";
		//如果被ABS的是最小负数，则会出现错误。。。。
		numerator = Math.abs(numerator);
		denominator = Math.abs(denominator);

		ArrayList<Integer> fracs = new ArrayList<Integer>();
		// key:余数，value：index in fracs
		Map<Integer, Integer> modsLocation = new HashMap<Integer, Integer>();

		// 处理整数部分
		int intPart = numerator / denominator;
		numerator %= denominator;
		retStr += intPart;
		// 如果整除直接返回
		if (numerator == 0)
			return retStr;

		retStr += ".";
		while (numerator != 0) {
			// 记录当前余数的位置
			modsLocation.put(numerator, fracs.size());
			// 先扩大10倍，再进行相除
			numerator *= 10;
			fracs.add(numerator / denominator);
			numerator %= denominator;
			// 如果包含相同的余数，则形成了循环
			if (modsLocation.containsKey(numerator))
				break;
		}

		// 如果能够被整除
		if (numerator == 0)
			for (int idx = 0; idx < fracs.size(); ++idx)
				retStr += fracs.get(idx);
		// 出现了循环
		else {
			// 处理非循环部分
			int recurEnd = modsLocation.get(numerator);
			for (int idx = 0; idx < recurEnd; ++idx)
				retStr += fracs.get(idx);
			// 处理循环部分
			retStr += "(";
			for (int idx = recurEnd; idx < fracs.size(); ++idx)
				retStr += fracs.get(idx);
			retStr += ")";
		}

		return retStr;
	}

	public static void main(String[] args) {
		FractiontoRecurringDecimal frd = new FractiontoRecurringDecimal();
		System.out.println(frd.fractionToDecimal(-23, 15));//failed case:-1, -2147483648

	}
}
