package cn.sspku.zx.aad.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray
 * [4,−1,2,1] has the largest sum = 6.
 * 
 * @author zhangxu
 * 
 */
public class MaximumSubarray {
	public int maxSubArray(int[] A) {
		if(null==A||A.length==0) return 0;
		
		int pre=A[0],max=pre;		
		for(int idx=1;idx<A.length;++idx)
		{
			pre=A[idx]+(pre<=0?0:pre);
			
			if(pre>max) max=pre;
		}
		
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
