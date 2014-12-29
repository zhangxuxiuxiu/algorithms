package cn.sspku.zx.aad.leetcode;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Credits: Special thanks to @ts for adding this problem and creating all test
 * cases.
 * 
 * @author zhangxu
 * 
 */
public class MajorityElement {
	public int majorityElement(int[] num) {
		if(null==num||num.length==0)
			return 0;
		
		int val=num[0],count=1;
		for(int idx=1;idx<num.length;++idx)
			if(val==num[idx])
				++count;
			else
			{
				if(count==0)
				{
					val=num[idx];
					count=1;
				}
				else --count;
			}
		
		return val;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
