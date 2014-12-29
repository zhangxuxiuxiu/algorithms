package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd",
 * "ce", "cf"].
 * 
 * @author zhangxu
 * 
 */
public class LetterCombinationsOfAPhoneNumber {
	public List<String> letterCombinations(String digits) {
		List<String> lst = new ArrayList<String>();
		if (null == digits)
			return lst;
		if (digits.length() == 0) {
			lst.add("");
			return lst;
		}
		
		char[][] digitToLetters = new char[][] { { 'a', 'b', 'c' },
				{ 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
				{ 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' },
				{ 'w', 'x', 'y', 'z' } };
		char[] word = new char[digits.length()];

		combine(lst, word, digits, digitToLetters, 0);

		return lst;
	}

	void combine(List<String> lst, char[] word, String digits, char[][] dtl,
			int digitIdx) {
		if (digitIdx == digits.length()) {
			lst.add(new String(word));
			return;
		}
		char[] letters = dtl[digits.charAt(digitIdx) - '2'];
		for (int p = 0; p < letters.length; ++p) {
			word[digitIdx] = letters[p];
			combine(lst, word, digits, dtl, digitIdx + 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
