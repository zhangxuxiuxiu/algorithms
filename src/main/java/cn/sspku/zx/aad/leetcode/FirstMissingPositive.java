package cn.sspku.zx.aad.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * @author zhangxu
 * 
 */
public class FirstMissingPositive {
	public int firstMissingPositive2(int[] A) {
		if (null == A)
			return -1;

		Set<Integer> hasher = new HashSet<Integer>();
		for (int idx = 0; idx < A.length; ++idx)
			if (A[idx] > 0)
				hasher.add(A[idx]);
		int start = 0;
		while (hasher.contains(++start))
			;

		return start;
	}

	/**
	 * failed cases: [-1,4,2,1,9,10] [1,1]
	 * 
	 * @param A
	 * @return
	 */
	public int firstMissingPositive(int[] A) {
		int digit, tpr;
		for (int idx = 0; idx < A.length; ++idx) {
			digit = A[idx];
			while (digit > 0 && digit <= A.length && digit != A[digit - 1]) {
				tpr = A[digit - 1];
				A[digit - 1] = digit;
				digit = tpr;
			}
		}
		int idx = 0;
		while (idx < A.length)
			if (A[idx] != ++idx)
				return idx;

		return A.length + 1;
	}

	/**
	 * 重新安排arr[start,end)区间内的数据，使得小于等于val的数在左边，大于val的数在右边
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 * @param val
	 * @return ret使得arr[start,ret)都小于等于val,arr[ret,end)都大于val
	 */
	/*
	 * private int rearrange(int[] arr, int start, int end, int val) { int left
	 * = start, right = end - 1, tpr; while (right > left) { while (right >=
	 * start && arr[right] > val) --right; while (left < end && arr[left] <=
	 * val) ++left; if (left < right) { tpr = arr[left]; arr[left] = arr[right];
	 * arr[right] = tpr; } }
	 * 
	 * return left;// [start,left)小于等于val }
	 */

	public static void main(String[] args) {
		FirstMissingPositive fmp = new FirstMissingPositive();
		System.out.println(fmp.firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		// [1,2,6,7],
		// [1,2,5,5,6],[1,2,4]
	}
}
