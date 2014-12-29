package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * 有N数据，寻找其中的K的最小值，时间复杂度为NlogK
 * 
 * @author zhangxu
 * 
 */
public class MinKValue {

	/**
	 * 在数组num中返回k个最小值
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public int[] minK(int[] num, int k) {
		if (null == num || num.length <= k)
			return num;

		int[] kMins = Arrays.copyOfRange(num, 0, k);

		buildMaxHeap(kMins);
		while (k < num.length) {
			if (num[k] < kMins[0]) {
				kMins[0] = num[k];
				adjust(kMins, 0);
			}
			++k;
		}

		return kMins;
	}

	/**
	 * @param kMins
	 */
	private void print(int[] kMins) {
		for (int idx = 0; idx < kMins.length; ++idx)
			System.out.printf("%5d", kMins[idx]);
	}

	// 构建大顶堆，从而可以用不断用更小的数替代大顶堆的最大值
	private void buildMaxHeap(int[] kMins) {
		if (null == kMins || kMins.length < 2)
			return;
		// 从右至左，可以省下很多步骤
		for (int idx = kMins.length / 2 - 1; idx >= 0; --idx)
			adjust(kMins, idx);
	}

	// suppose all args are legal
	private void adjust(int[] kMins, int idx) {
		if (idx < kMins.length / 2) {
			// 找到父节点，左右子节点中最大值的下标
			int maxIdx = idx;
			if (kMins[maxIdx] < kMins[2 * idx + 1])
				maxIdx = 2 * idx + 1;
			if (2 * idx + 2 < kMins.length
					&& kMins[maxIdx] < kMins[2 * idx + 2])
				maxIdx = 2 * idx + 2;

			// 调整最大堆
			if (idx != maxIdx) {
				swap(kMins, idx, maxIdx);
				adjust(kMins, maxIdx);
			}
		}
	}

	// suppose all args are legal
	private void swap(int[] kMins, int one, int two) {
		if (one != two) {
			int tpr = kMins[one];
			kMins[one] = kMins[two];
			kMins[two] = tpr;
		}
	}

	public static void main(String[] args) {
		int[] num = new int[] { 9, 38, 4, 8, 6, 7, 97 };
		MinKValue mkv = new MinKValue();
		mkv.print(mkv.minK(num, 4));
	}
}
