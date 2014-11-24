package cn.sspku.zx.aad.daily;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column. 1,2,3 → 1,3,2 3,2,1 →
 * 1,2,3 1,1,5 → 1,5,1
 * 
 * @author zhangxu
 * 
 */
public class NextPermutation {
	public void nextPermutation(int[] num) {
		if (null == num || num.length < 2)
			return;

		int idx = num.length - 2;
		// 从右到左寻找第一对左边小于右边的数字对
		while (idx > -1 && num[idx] >= num[idx + 1])
			--idx;
		// 如果找到了
		if (idx != -1) {
			int i = num.length - 1;
			while (num[i] <= num[idx])
				--i;
			swap(num, idx, i);
			Arrays.sort(num, idx + 1, num.length);
		} else {
			// 倒置整个数组
			int left = 0, right = num.length - 1;
			while (left < right)
				swap(num, left++, right--);
		}
	}

	void swap(int[] num, int i, int j) {
		if (i != j) {
			int tpr = num[i];
			num[i] = num[j];
			num[j] = tpr;
		}
	}

	public static void main(String[] args) {
		NextPermutation np = new NextPermutation();
		int[] num = new int[] { 2, 3, 3, 1 };
		np.nextPermutation(num);
		for (int idx = 0; idx < num.length; ++idx)
			System.out.printf("%5d", num[idx]);

	}
}
