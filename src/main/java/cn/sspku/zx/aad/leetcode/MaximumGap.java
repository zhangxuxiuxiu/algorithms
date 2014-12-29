package cn.sspku.zx.aad.leetcode;

/**
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in
 * the 32-bit signed integer range.
 * 
 * @author zhangxu
 * 
 */
public class MaximumGap {
	private Cell[] cells;

	private class Cell {
		private int max, min;

		Cell() {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
		}

		void take(int val) {
			if (val > max)
				max = val;
			if (val < min)
				min = val;
		}
	}

	public int maximumGap(int[] num) {
		if (null == num || num.length < 2)
			return 0;

		buildCells(num);
		int left = cells[0].min, maxGap = 0, gap;
		for (int idx = 0; idx < cells.length; ++idx) {
			while (idx < cells.length && cells[idx] == null)
				++idx;
			gap = cells[idx].min - left;
			if (gap > maxGap)
				maxGap = gap;
			left = cells[idx].max;
		}
		return maxGap;
	}

	/**
	 * @param num
	 */
	private void buildCells(int[] num) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int idx = 0; idx < num.length; ++idx) {
			if (num[idx] > max)
				max = num[idx];
			if (num[idx] < min)
				min = num[idx];
		}

		double avgGap = (max - min) / (num.length - 1.0);
		cells = new Cell[num.length];
		int curIdx;
		for (int idx = 0; idx < num.length; ++idx) {
			curIdx = (int) ((num[idx] - min) / avgGap);
			if (null == cells[curIdx])
				cells[curIdx] = new Cell();
			cells[curIdx].take(num[idx]);
		}
	}

	public static void main(String[] args) {
		System.out.println(new MaximumGap().maximumGap(new int[] { 2, 4, 1,8 }));
	}
}
