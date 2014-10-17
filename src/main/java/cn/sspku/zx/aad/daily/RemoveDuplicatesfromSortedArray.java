package cn.sspku.zx.aad.daily;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array A = [1,1,2],
 * 
 * Your function should return length = 2, and A is now [1,2].
 * 
 * @author zhangxu
 * 
 */
public class RemoveDuplicatesfromSortedArray {
	public int removeDuplicates(int[] A) {
		if (null == A || A.length == 0)
			return 0;

		int counter = 0, idx = 0, finder = 0, times = 0, elem;
		while (finder < A.length) {
			// 记录当前被比较的元素
			elem = A[finder++];
			times = 0;
			//寻找下一个不相等的元素
			while (finder < A.length && elem == A[finder]) {
				++finder;
				++times;
			}

			A[idx++] = elem;
			counter += times;
		}

		return A.length - counter;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
