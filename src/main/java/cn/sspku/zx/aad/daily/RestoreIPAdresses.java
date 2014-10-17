package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @author zhangxu
 * 
 */
public class RestoreIPAdresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> ips = new ArrayList<String>();
		if (null == s || s.length() < 4)
			return ips;

		restore(ips, new int[4], 1, s, 0);

		return ips;
	}

	void restore(List<String> ips, int[] ipSegs, int seg, String s, int start) {
		if (seg > 4) {
			ips.add(stringify(ipSegs));
			return;
		}

		int minL, _minL, maxL, _maxL, val;
		String subStr;

		// 计算该子段最短的长度，从而保证后面的子段不存在长度超过3的情况
		minL = 1;
		_minL = s.length()-start - 3 * (4 - seg);
		if (_minL > minL)
			minL = _minL;

		// 计算该子段最长的长度，从而保证后面的子段不存在长度短于1的情况
		maxL = 3;
		_maxL = s.length()-start - 1 * (4 - seg);
		if (_maxL < maxL)
			maxL = _maxL;

		// 处理没种分段情况
		for (int l = minL; l <= maxL; ++l) {
			subStr = s.substring(start, start + l);
			if (subStr.startsWith("0") && subStr.length() > 1)
				return;

			val = Integer.parseInt(subStr);
			if (val < 256) {
				ipSegs[seg-1] = val;
				// 递归下一段子IP
				restore(ips, ipSegs, seg + 1, s, start + l);
			}
		}
	}

	// 把四个数字转化为字符串的IP
	String stringify(int[] ipSegments) {
		String ip = "" + ipSegments[0];
		for (int idx = 1; idx < ipSegments.length; ++idx)
			ip += "." + ipSegments[idx];

		return ip;
	}

	void print(List<String> ips) {
		Iterator<String> it = ips.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) {
		String s = "11111";
		
		RestoreIPAdresses ria=new RestoreIPAdresses();
		ria.print(ria.restoreIpAddresses(s));
	}
}
