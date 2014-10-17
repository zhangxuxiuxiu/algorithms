package cn.sspku.zx.aad.daily;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * For example, Given s = "Hello World", return 5.
 * 
 * @author zhangxu
 * 
 */
public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		if(null==s||s.length()==0) return 0;
		String[] words=s.split(" ");
		for(int idx=words.length-1;idx>=0;--idx)
		{
			if(!words[idx].equals("")) return words[idx].length();
		}
		
		return 0;
	}
}
