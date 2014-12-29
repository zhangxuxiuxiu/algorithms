package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3) should
 * return "PAHNAPLSIIGYIR".
 * 
 * @author zhangxu
 * 
 */
public class ZigZagConversion {
	public String convert(String s, int nRows) {
		if (null == s || nRows <= 1 || nRows >= s.length())
			return s;

		int k = (int) Math.ceil((0.0 + s.length()) / (2 * nRows - 2))
				* (nRows - 1);
		System.out.println("k=" + k);
		char[][] zigZag = new char[nRows][k];
		for (int idx = 0; idx < nRows; ++idx)
			Arrays.fill(zigZag[idx], ' ');

		int kth = 0, idx = 0;

		while (true) {
			for (int r = 0; r < nRows; ++r)
				if (idx < s.length())
					zigZag[r][kth] = s.charAt(idx++);
				else
					break;
			for (int r = 2; r < nRows; ++r)
				if (idx < s.length())
					zigZag[nRows - r][++kth] = s.charAt(idx++);
				else
					break;
			++kth;
			if (idx == s.length())
				break;
		}

		for (int r = 0; r < nRows; ++r) {
			for (int c = 0; c < k; ++c) {
				System.out.printf("%5c", zigZag[r][c]);
			}
			System.out.println();
		}

		char[] ret = new char[s.length()];
		idx = 0;
		for (int r = 0; r < nRows; ++r)
			for (int c = 0; c < k; ++c)
				if (zigZag[r][c] != ' ')
					ret[idx++] = zigZag[r][c];

		return new String(ret);
	}

	public static void main(String[] args) {
		ZigZagConversion zzc = new ZigZagConversion();
		Utils.Print(zzc.convert("ABCDEF", 4));// ABC 2->ACB; ABCD 2->ACBD
	}
}
