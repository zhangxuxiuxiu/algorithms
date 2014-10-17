package cn.sspku.zx.aad.daily;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints: Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction
 * of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 * 
 * @author zhangxu
 * 
 */
public class PalindromeNumber {
	/**
	 * 需要考虑的特殊情况：1》X小于零；2》不能WHILE（X>=10）因为出现1021这种情况时2小于10，此时忽略了0，会错误的认为是回文；3》x
	 * %= pows;x /= 10;顺序颠倒会可能出现DIVIDEB YZERO的情况
	 * 
	 * @param x
	 * 
	 * @return
	 */
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;

		int pows = (int) Math.pow(10, (int) Math.log10(x));
		int a, b;
		while (x > 0) {
			a = x % 10;
			b = x / pows;
			if (a != b)
				return false;

			x %= pows;
			x /= 10;
			pows /= 100;
		}

		return true;
	}

	public static void main(String[] args) {
		PalindromeNumber pm = new PalindromeNumber();
		System.out.println(pm.isPalindrome(1000021));
	}
}
