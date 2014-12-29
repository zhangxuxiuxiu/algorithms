package cn.sspku.zx.aad.leetcode;

/**
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 * @author zhangxu
 * 
 */
public class ReverseInteger {
	public int reverse(int x) {
		int sign=x>0?1:-1;
		int value=Math.abs(x);
		
		int a=value,ret=0;
		while(a>0)
		{
			ret=ret*10+a%10;
			a/=10;
		}
		
		return sign*ret;
	}
	
	public static void main(String[] args)
	{
		int a=-120;
		System.out.println(""+a+"  ---> "+new ReverseInteger().reverse(a));
	}
}
