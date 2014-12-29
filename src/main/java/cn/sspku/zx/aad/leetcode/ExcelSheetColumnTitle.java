package cn.sspku.zx.aad.leetcode;

/**
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * 
 * For example:
 * 
 * 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
 * 
 * @author zhangxu
 * 
 */
public class ExcelSheetColumnTitle {
	public String convertToTitle(int n) {
		if (n < 1)
			return null;

		String str = "";
		while (n > 0) {
			str = (char) ('A' + (n - 1) % 26) + str;
			n = (n - 1) / 26;
		}

		return str;
	}

	public static void main(String[] args) {
		ExcelSheetColumnTitle esct = new ExcelSheetColumnTitle();
		System.out.println(esct.convertToTitle(27));

	}

}
