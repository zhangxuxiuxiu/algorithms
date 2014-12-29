package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * @author zhangxu
 * 
 */
public class SortColors {
	public void sortColors(int[] A) {
		if (null == A || A.length < 2)
			return;
		int red=0,white=0;
		
		for(int idx=0;idx<A.length;++idx)	
		{
			switch(A[idx])
			{
				case 0:
				{
					++red;
					break;
				}
				case 1:
				{
					++white;
					break;
				}
			}
		}
		
		Arrays.fill(A,0,red,0);
		Arrays.fill(A,red,red+white,1);
		Arrays.fill(A,red+white,A.length,2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
