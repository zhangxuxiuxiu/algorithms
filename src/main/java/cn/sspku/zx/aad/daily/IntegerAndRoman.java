package cn.sspku.zx.aad.daily;

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author zhangxu
 * 
 */
public class IntegerAndRoman {
	public String intToRoman(int num) {
		String[][] digits = new String[][] {
				{ "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "M", "MM", "MMM" } };

		int bits = 0, digit;
		String ret = "";
		while (num > 0) {
			digit = num % 10;
			num /= 10;
			if (0 != digit)
				ret = digits[bits][digit - 1] + ret;

			++bits;
		}

		return ret;
	}

	/*
	 * Given a roman numeral, convert it to an integer.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 */
	public int romanToInt(String s) {
		String[][] digits = new String[][] {
				{ "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "M", "MM", "MMM" } };

		int base = 0,matchIdx;
		String matchStr;
		for (int level = digits.length - 1; level > -1; --level) {
			//在该级别进行匹配
			for (matchIdx = digits[level].length - 1; matchIdx >-1;--matchIdx) {
				matchStr = digits[level][matchIdx];
                //匹配成功后进行处理
				if (s.length() >= matchStr.length()
						&& matchStr.equals(s.subSequence(0, matchStr.length()))) {
					base = base * 10 + (matchIdx + 1);
					System.out.println(matchIdx+1);
					s = s.substring(matchStr.length(),
							s.length());
					break;
				}
			}
			//匹配失败，说明该层级为零
			if(matchIdx ==-1)
			    base*=10;
		}

		return base;
	}

	public static void main(String[] args) {
		IntegerAndRoman iar=new IntegerAndRoman();
		System.out.println(iar.romanToInt("DCXXI"));
	}
}
