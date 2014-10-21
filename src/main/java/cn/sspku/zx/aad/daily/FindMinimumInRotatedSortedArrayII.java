package cn.sspku.zx.aad.daily;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are
 * allowed?
 * 
 * Would this affect the run-time complexity? How and why? Suppose a sorted
 * array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * @author zhangxu
 * 
 */
public class FindMinimumInRotatedSortedArrayII {
	public int findMin(int[] num) {
		if (null == num || num.length == 0)
			return 0;

		int start = 0, end = num.length - 1, mid;
		while (end > start) {
			mid = (start + end) / 2;
			if (num[start] <= num[mid])
				if (num[start] > num[end])
					start = mid + 1;
				else if (num[start] == num[end])
					--end;
				else
					return num[start];
			else
				end = mid;
		}

		return num[start];
	}

	public static void main(String[] args) {
		FindMinimumInRotatedSortedArrayII fmirsa = new FindMinimumInRotatedSortedArrayII();
		int[] arr = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(fmirsa.findMin(arr));
	}
}
