package cn.sspku.zx.aad.leetcode;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 * 
 * @author zhangxu
 * 
 */
public class FindPeakElement {
	/**
	 * The solution should be in logarithmic complexity.
	 * 
	 * 方案描述，对于任意一个中间位置的元素，如果就是peak，则直接返回；否则左边大搜索左边，右边大搜索右边
	 * 
	 * @param num
	 * @return
	 */
	public int findPeakElement(int[] num) {
		if (null == num || num.length == 0)
			return -1;

		int left = 0, right = num.length - 1, mid;
		while (left < right) {
			mid = (left + right) / 2;
			// 右边大，搜索右边
			if (num[mid] < num[mid + 1])
				left = mid + 1;
			// 本身是peak，直接返回
			else if (mid > 0 && num[mid - 1] < num[mid])
				return mid;
			// 左边大，搜索左边
			else
				right = mid - 1;
		}
		return left;
	}

	public static void main(String[] args) {
		int[] num = new int[] { 1, 2 };
		FindPeakElement fpe = new FindPeakElement();
		System.out.println(fpe.findPeakElement(num));
	}
}
