package cn.sspku.zx.aad.leetcode;

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 * 
 * @author zhangxu
 * 
 */
public class SearchForARange {
	public int[] searchRange(int[] A, int target) {
		int[] range = new int[] { -1, -1 };

		if (null == A || A.length ==0)
			return range;

		int left = 0, right = A.length - 1, mid=-1;
		while (left <= right) {
			mid = (left + right) / 2;
			if (A[mid] == target)
				break;
			if (A[mid] > target)
				right = mid - 1;
			else
				left = mid + 1;
		}

		if (A[mid] == target) {
			left=mid-1;
			while(left>=0&&A[left]==target) --left;
			range[0]=left+1;
			
			right=mid+1;
			while(right<A.length&&A[right]==target) ++right;
			range[1]=right-1;			
		}
		
		return range;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
