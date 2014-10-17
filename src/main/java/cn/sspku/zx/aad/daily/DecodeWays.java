package cn.sspku.zx.aad.daily;

/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 * 
 * @author zhangxu
 * 
 */
public class DecodeWays {
	public int numDecodings(String s) {
		if (null == s || s.length() == 0)
			return 0;
		
		if(s.startsWith("0")) return 0;
		if(s.length()<2) return 1;
		else
		{
			for(int idx=1;idx<s.length();++idx)
			{
				if(s.charAt(idx)=='0'&&s.charAt(idx-1)!='2'&&s.charAt(idx-1)!='1') return 0;			
			}
		}

		int[] counters = new int[s.length()];
		counters[0] = 1;
		if(Integer.parseInt(s.substring(0, 2)) > 26||s.charAt(1) == '0')
	    	counters[1] = 1;
		else
			counters[1] = 2;

		int p = 2;
		while (p < s.length()) {
			if (s.charAt(p) == '0' )
			    counters[p]=counters[p-2];
			else if(s.charAt(p - 1) == '0'
					|| Integer.parseInt(s.substring(p - 1, p + 1)) > 26)
				counters[p] = counters[p - 1];
			else
				counters[p] = counters[p - 1] + counters[p - 2];
			
			++p;
		}

		return counters[counters.length-1];
	}

	public static void main(String[] args) {
		String s="9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253";
		DecodeWays dw=new DecodeWays();
		System.out.println(dw.numDecodings(s));

	}

}
