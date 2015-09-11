package cn.sspku.zx.aad.InterviewQs;

import java.util.Arrays;

/**
 * Partition problem is to determine whether a given set can be partitioned into
 * two subsets such that the sum of elements in both subsets is same.
 * 
 * @author zhangxu
 * 
 */
public class PartitionProblem {
	/**
	 * @solution Following are the two main steps to solve this problem: 1)
	 *           Calculate sum of the array. If sum is odd, there can not be two
	 *           subsets with equal sum, so return false. 2) If sum of array
	 *           elements is even, calculate sum/2 and find a subset of array
	 *           with sum equal to sum/2.
	 * 
	 * @param arr
	 * @return
	 */
	public boolean isSeparable(int[] arr) {
		// corner case
		if (null == arr || arr.length == 0)
			return false;

		// calculate the sum of the array
		int sum = 0, idx = 0;
		while (idx < arr.length)
			sum += arr[idx++];
		// if the sum is odd, surely the array is not separable
		if (sum % 2 == 1)
			return false;
		// if the sum is even, find a subset which sums up to sum/2
		return ifSumExisted(arr, sum / 2);
	}

	/**
	 * recursive algorithm
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 * @param sum
	 * @return
	 * 
	 *         private boolean ifSumExisted(int[] arr, int start, int sum) { //
	 *         base case if (start >= arr.length || sum < 0) return false; if
	 *         (sum == arr[start]) return true;
	 * 
	 *         // arr[start] is in subset or not return ifSumExisted(arr, start
	 *         + 1, sum) || ifSumExisted(arr, start + 1, sum - arr[start]); }
	 */

	private boolean ifSumExisted(int[] arr, int sum) {
		// corner cases
		if (null == arr || arr.length == 0 || sum < 0)
			return false;

		boolean[][] table = new boolean[sum + 1][arr.length + 1];
		// initialize the first row and first column
		Arrays.fill(table[0], true);
		for (int idx = 0; idx < sum + 1; ++idx)
			table[idx][0] = false;

		// dynamic programming: F(sum,idx)=F(sum-arr[idx],idx-1)||F(sum,idx-1)
		for (int idx = 1; idx <= arr.length; ++idx)
			for (int s = 1; s <= sum; ++s)
				if (s >= arr[idx - 1])
					table[s][idx] = table[s - arr[idx - 1]][idx - 1]
							|| table[s][idx - 1];

		return table[sum][arr.length];
	}

	public static void main(String[] args) {
		PartitionProblem pp = new PartitionProblem();
		int[] arr = new int[] { 1, 5, 11, 5 };
		System.out.println(pp.isSeparable(arr));
	}
}
