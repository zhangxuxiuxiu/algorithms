package cn.sspku.zx.aad.leetcode;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples. [1,3,5,6], 5 → 2 [1,3,5,6], 2 → 1 [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * 
 * @author zhangxu
 * 
 */
public class SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
		if (null == A || A.length == 0)
			return 0;

		int left = 0, right = A.length - 1, mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (target == A[mid])
				return mid;
			if (target > A[mid])
				left = mid + 1;
			else
				right = mid - 1;
		}

		if (right >= 0 && A[right] < target)
			return right + 1;
		if (left < A.length && A[left] > target)
			return left;
		
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
