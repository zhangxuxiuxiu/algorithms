package cn.sspku.zx.aad.daily;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author zhangxu
 * 
 */
public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] num) {
		if (null == num || num.length == 0)
			return 0;

		int start = 0, end = num.length - 1, mid;
		while (end > start) {
			mid = (start + end) / 2;
			if (num[start] <= num[mid])
				if (num[start] > num[end])
					start = mid + 1;
				else
					return num[start];
			else
				end = mid;
		}

		return num[start];
	}

	public static void main(String[] args) {
		FindMinimumInRotatedSortedArray fmirsa = new FindMinimumInRotatedSortedArray();
		int[] arr = new int[] { 0, 1, 2 };
		System.out.println(fmirsa.findMin(arr));
	}
}
