package cn.sspku.zx.aad.daily;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 * 
 * @author zhangxu
 * 
 */
public class MedianOfTwoSortedArrays {
	/**
	 * 返回两个非递减序列的中位数
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		// 如果两个数组元素总数为奇数，则lMid与rMid相等，都为中间数的序列数，否则分别为中间的两个数
		int lMid = (A.length + B.length + 1) / 2, rMid = (A.length + B.length + 2) / 2;
		double val = findKthElementInSortedArrays(A, B, lMid);
		if (lMid != rMid)
			val = (val + findKthElementInSortedArrays(A, B, rMid)) / 2;
		return val;
	}

	/**
	 * 从两个非递减排序数组中选出第K小的数
	 * 
	 * @param A
	 * @param B
	 * @param k
	 * @return
	 */
	public double findKthElementInSortedArrays(int A[], int B[], int k) {
		// 边界检查
		if (A == null)
			if (B != null && k > 0 && k < B.length)
				return B[k];
			else
				return -1;

		if (B == null)
			if (A != null && k > 0 && k < A.length)
				return A[k];
			else
				return -1;

		if (A == null && B == null || k < 1 || k > A.length + B.length)
			return -1;

		// 在确保各项参数都合法的情况下，寻找第K小的数
		int aStart = 0, aEnd = A.length, aMid, bStart = 0, bEnd = B.length, bMid;
		while (true) {
			if (aStart == aEnd)
				return B[bStart + k - 1];
			if (bStart == bEnd)
				return A[aStart + k - 1];

			if (k == 1)
				return Math.min(A[aStart], B[bStart]);
			if (k == aEnd - aStart + bEnd - bStart)
				return Math.max(A[aEnd - 1], B[bEnd - 1]);

			if (A[aEnd - 1] <= B[bStart])
				if (k <= aEnd - aStart)
					return A[aStart + k - 1];
				else
					return B[bStart + k - aEnd + aStart - 1];
			if (B[bEnd - 1] <= A[aStart])
				if (k <= bEnd - bStart)
					return B[bStart + k - 1];
				else
					return A[aStart + k - bEnd + bStart - 1];

			if (aEnd == aStart + 1)
				return kthMinValueInArrayPlusOne(B, bStart, bEnd, k, A[aStart]);

			if (bEnd == bStart + 1)
				return kthMinValueInArrayPlusOne(A, aStart, aEnd, k, B[bStart]);

			aMid = (aStart + aEnd) / 2;
			bMid = (bStart + bEnd) / 2;

			if (k <= aMid - aStart + bMid - bStart) {
				if (A[aMid] < B[bMid]) {
					bEnd = bMid;
				} else if (A[aMid] > B[bMid]) {
					aEnd = aMid;
				} else {
					aEnd = aMid;
					bEnd = bMid;
				}
			} else {
				if (A[aMid] < B[bMid]) {
					k -= (aMid - aStart);
					aStart = aMid;
				} else if (A[aMid] > B[bMid]) {
					k -= (bMid - bStart);
					bStart = bMid;
				} else {
					k -= (aMid - aStart + bMid - bStart);
					aStart = aMid;
					bStart = bMid;
				}
			}
		}
	}

	/**
	 * 在非递减数组array的[start,end)的元素和val中找到第K小的元素
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @param k
	 * @param val
	 * @return
	 */
	private double kthMinValueInArrayPlusOne(int[] array, int start, int end,
			int k, int val) {
		if (null == array)
			return -1;
		if (start < 0 || end > array.length)
			return -1;
		if (k < 1 || k > end - start + 1)
			return -1;

		int idx = maxSmallerElementIndex(array, start, end, val);
		if (k < idx - start + 2)
			return array[start + k - 1];
		if (k == idx - start + 2)
			return val;
		return array[start + k - 2];
	}

	/**
	 * 在非递减数组arr[start,end)中找到小于val的最大元素的下标
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 * @param val
	 * @return
	 */
	private int maxSmallerElementIndex(int[] arr, int start, int end, int val) {
		if (null == arr || start < 0 || end > arr.length || end <= start)
			return -1;
		if (val < arr[start])
			return start - 1;
		if (val > arr[end - 1])
			return end - 1;

		int mid;
		while (start < end) {
			mid = (start + end) / 2;

			if (arr[mid] <= val)
				if (val < arr[mid + 1])
					return mid;
				else
					start = mid + 1;
			if (arr[mid] > val)
				end = mid;
		}

		return -1;
	}

	public static void main(String[] args) {
		MedianOfTwoSortedArrays motsa = new MedianOfTwoSortedArrays();

		int[] A = new int[] { 1, 3, 5 }, B = new int[] { 2, 4, 6 };
		for (int k = 1; k <= A.length + B.length; ++k)
			System.out.println(motsa.findKthElementInSortedArrays(A, B, k));

		System.out.println(motsa.findMedianSortedArrays(A, B));
	}
}
