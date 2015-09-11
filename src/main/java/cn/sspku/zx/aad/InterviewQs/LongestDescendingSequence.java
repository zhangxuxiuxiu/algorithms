package cn.sspku.zx.aad.InterviewQs;

import java.util.LinkedList;
import java.util.List;

import cn.sspku.zx.aad.leetcode.Utils;

/**
 * 求一个数组的最长递减子序列 比如{9，4，3，2，5，4，3，2}的 最长递减子序列为{9，5，4，3，2}
 * 
 * @author zhangxu
 * 
 */
public class LongestDescendingSequence {
	/**
	 * 采用动态递归的方法，使用两个数组，一个记录当前元素所述子串的前一个位置，一个记录以当前位置结束的所有子串中最长子串的长度
	 * 
	 * @param sequence
	 * @return
	 */
	public List<Integer> LDS(int[] sequence) {
		if (null == sequence || sequence.length == 0)
			return null;

		LinkedList<Integer> seq = new LinkedList<Integer>();
		int[] preIndexes = new int[sequence.length], maxLens = new int[sequence.length];
		int maxLength = 1, maxLenIndex = 0;

		for (int idx = 0; idx < sequence.length; ++idx) {
			// 在左边找到一个更大且所在子串最长的元素的下标
			int pre, len = 0, preIndex = -1;
			for (pre = idx - 1; pre > -1; --pre)
				if (sequence[pre] > sequence[idx] && maxLens[pre] > len) {
					preIndex = pre;
					len = maxLens[pre];
				}

			preIndexes[idx] = preIndex;
			maxLens[idx] = len + 1;
			if (maxLens[idx] > maxLength) {
				maxLength = maxLens[idx];
				maxLenIndex = idx;
			}
		}

		int idx = maxLenIndex;
		while (idx != -1) {
			seq.addFirst(sequence[idx]);
			idx = preIndexes[idx];
		}

		return seq;
	}

	public static void main(String[] args) {
		LongestDescendingSequence lds = new LongestDescendingSequence();
		int[] seq = new int[] { 9, 4, 3, 2, 5, 4, 3, 2 };
		Utils.PrintIterableInt(lds.LDS(seq));
	}
}
