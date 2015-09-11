package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * @author zhangxu
 * 
 */

public class LargestNumber {
	public String largestNumber(int[] num) {
		if (null == num || num.length == 0)
			return "";

		// transform int array into NumUnit array
		List<NumUnit> units = new ArrayList<NumUnit>(num.length);
		for (int idx = 0; idx < num.length; ++idx)
			units.add(new NumUnit(num[idx]));

		// sort the units
		Collections.sort(units);

		// generate the result string
		String ret = "";
		Iterator<NumUnit> it = units.iterator();
		while (it.hasNext()) {
			ret += it.next().num;
		}

		//in case there are only multiple zeros in the array num
		if(ret.startsWith("0"))
			return "0";
		
		return ret;
	}

	private class NumUnit implements Comparable<NumUnit> {
		private String num;

		public NumUnit(int val) {
			num = "" + val;
		}

		public NumUnit setNum(String _num) {
			num = _num;
			return this;
		}

		/**
		 * 从左到右依次比较每个数字，如果有大小，则比出大小; 如果直到某一个比较结束都没有比较出结果，则将长的那个多出的部分做一个新对象进行
		 */
		public int compareTo(NumUnit v) {
			String num2 = v.num;
			int idx = 0, len = Math.min(num.length(), num2.length());

			while (idx < len) {
				if (num.charAt(idx) > num2.charAt(idx))
					return -1;
				if (num.charAt(idx) < num2.charAt(idx))
					return 1;
				++idx;
			}

			if (num.length() > len)
				return new NumUnit(0).setNum(num.substring(idx)).compareTo(v);

			if (num2.length() > len)
				return compareTo(new NumUnit(0).setNum(num2.substring(idx)));

			return 0;
		}
	}

	public static void main(String[] args) {
		LargestNumber ln = new LargestNumber();
		int[] in = new int[] { 31, 3132, 34, 5, 9 };

		System.out.println(ln.largestNumber(in));
	}
}
