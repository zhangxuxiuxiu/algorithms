package cn.sspku.zx.aad.daily;

/**
 * Implement strStr().
 * 
 * Returns a pointer to the first occurrence of needle in haystack, or null if
 * needle is not part of haystack.
 * 
 * @author zhangxu
 * 
 */
public class ImplementStrStr {
	// KMP算法？？？
	public String strStr(String haystack, String needle) {
		if (haystack == null || needle == null
				|| haystack.length() < needle.length())
			return null;

		int hIdx = 0, nIdx = 0,length=0;
		while(hIdx<haystack.length()-needle.length())
		{
			
		}
		
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
