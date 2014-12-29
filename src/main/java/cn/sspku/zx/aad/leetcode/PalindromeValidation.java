package cn.sspku.zx.aad.leetcode;

public class PalindromeValidation {

	public boolean isPalindrome(String s) {
		s = s.toLowerCase();

		if (s.length() > 1) {
			int left = -1, right = s.length();
			while (++left < --right) {
				
				while (left < s.length() && !isValidLetter(s.charAt(left)))
					++left;
				while (right >= 0 && !isValidLetter(s.charAt(right)))
					--right;
				if (left<s.length()&&right>=0&&(s.charAt(left) != s.charAt(right)))
					return false;
			}
		}

		return true;
	}

	private static boolean isValidLetter(char charAt) {

		return (charAt >= 'a' && charAt <= 'z')||(charAt>='0'&&charAt<='9');
	}

	public static void main(String[] args) {
		PalindromeValidation pv = new PalindromeValidation();
		String s = "1a2";
		System.out.println(s + " is palindrome ? " + pv.isPalindrome(s));
	}

}
