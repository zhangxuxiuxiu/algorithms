package cn.sspku.zx.aad.leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author zhangxu
 * 
 */
public class RotatedSortedArray {
	public int search(int[] A, int target) {
		if (null != A&&A.length!=0) {
			int idx=1;
			//找出第二个片段的起始值坐标
			while(idx<A.length&&A[idx-1]<A[idx])++idx;
			
			int left=0,right=A.length-1,mid;
			while(left<=right)
			{
				mid=(left+right)/2;
				if(A[(mid+idx)%A.length]==target) return (mid+idx)%A.length;
				if(A[(mid+idx)%A.length]>target) right=mid-1;
				else left=mid+1;
			}
		}
		
		return -1;
	}
}
