package cn.sspku.zx.aad.daily;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211. Given an integer n, generate the nth
 * sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * @author zhangxu
 * 
 */
public class CountAndSay {
	public String countAndSay(int n) {
		if (n < 1)
			return "";

		String digits = "1", dig;
		int rounds = 1, time, idx;
		char c;

		while (rounds++ < n) {
			dig = "";
			idx = 0;
			time = 0;
			c = digits.charAt(0);
			while (idx < digits.length()) {
				if (c == digits.charAt(idx))
					++time;
				else {
					dig += ("" + time + ""+c);
					//System.out.println("output dig: "+dig);
					c = digits.charAt(idx);
					time = 1;
				}
				++idx;
			}
			//处理最后一串相同的数字
			dig += ("" + time + ""+c);
			digits = dig;
			System.out.println(dig);
		}

		return digits;
	}

	public static void main(String[] args) {
		CountAndSay cas=new CountAndSay();
		cas.countAndSay(5);

	}
}
