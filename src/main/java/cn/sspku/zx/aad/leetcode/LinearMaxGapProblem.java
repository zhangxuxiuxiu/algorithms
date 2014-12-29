package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;

/*给定n个实数，求着n个实数在实轴上向量2个数之间的最大差值，要求线性的时间算法。

 方案：最先想到的方法就是先对这n个数据进行排序，然后一遍扫描即可确定相邻的最大间隙。但该方法不能满足线性时间的要求。故采取如下方法：
 1>找到n个数据中最大和最小数据max和min。
 2>用n-2个点等分区间[min, max]，即将[min, max]等分为n-1个区间（前闭后开区间），将这些区间看作桶，编号为，且桶i 的上界和桶i+1的下届相同，
 即每个桶的大小相同。每个桶的大小为：。实际上，这些桶的边界构成了一个等差数列（首项为min，公差为），且认为将min放入第一个桶，将max放入第n-1个桶。
 将n个数放入n-1个桶中：将每个元素x[i] 分配到某个桶（编号为index），其中（这括号里多了个“+”），并求出分到每个桶的最大最小数据。
 3>最大间隙：除最大最小数据max和min以外的n-2个数据放入n-1个桶中，由抽屉原理可知至少有一个桶是空的，又因为每个桶的大小相同，所以最大间隙不会在同一桶中出现，
 一定是某个桶的上界和气候某个桶的下界之间隙，且该量筒之间的桶（即便好在该连个便好之间的桶）一定是空桶。也就是说，最大间隙在桶i的上界和桶j的下界之间产生j>=i+1。一遍扫描即可完成。
 */
//LinearMaxGapProblem<T extends LinearMaxGapProblem.Entry<? extends Comparable<?>>>
//LinearMaxGapProblem<T extends Comparable<T>, U extends LinearMaxGapProblem.Entry<T>>
public class LinearMaxGapProblem {

	public Integer maxGap(ArrayList<Integer> array) throws Exception {
		// 检查输入边界
		if (array == null)
			throw new NullPointerException("the input array is empty!!!");
		if (array.size() < 2)
			return 0;

		Integer max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		double avgGap;
		// 计算最大最小值和平均间隔值
		for (int idx = 0; idx < array.size(); ++idx) {
			if (array.get(idx) > max)
				max = array.get(idx);
			if (array.get(idx) < min)
				min = array.get(idx);
		}
		// 分成（N-1）个桶
		avgGap = (max - min + 0.00) / (array.size() - 1);

		// 初始化桶数组
		ArrayList<Entry> _tmpArray = new ArrayList<Entry>(array.size() - 1);
		for (int idx = 0; idx < array.size() - 1; ++idx) {
			_tmpArray.add(new Entry());
		}

		// 根据输入数据来调整每一个桶的最大最小值
		for (int idx = 0; idx < array.size(); ++idx) {
			// 计算每一个数据对应的桶号
			int index = (int) Math.floor((array.get(idx) - min) / avgGap);
			if (index == array.size() - 1)// 如果是最大值则放入最后一个桶
				index = array.size() - 2;

			_tmpArray.get(index).adjust(array.get(idx));
		}

		//计算最大间隔
		int maxGap = Integer.MIN_VALUE, leftValue = Integer.MAX_VALUE, rightValue = Integer.MIN_VALUE;
		Entry entry;
		for (int idx = 0; idx < _tmpArray.size() - 1; ++idx) {
			// 找到相邻左边界
			if (rightValue != Integer.MIN_VALUE)
				leftValue = rightValue;
			else {
				boolean leftBoundNotFound = true;
				while (leftBoundNotFound && idx < _tmpArray.size() - 1) {
					entry = _tmpArray.get(idx);

					if (entry.max != Integer.MIN_VALUE) {
						leftValue = entry.max;
						break;
					} else
						++idx;
				}
			}

			// 找到相邻右边界
			boolean rightBoundNotFound = true;
			while (rightBoundNotFound && idx < _tmpArray.size() - 1) {
				entry = _tmpArray.get(idx + 1);
				if (entry.min != Integer.MAX_VALUE) {
					rightValue = entry.min;
					break;
				} else
					++idx;
			}

			//判断当前间隔是否是更大间隔
			if (rightValue - leftValue > maxGap)
				maxGap = rightValue - leftValue;
		}

		return maxGap;
	}

	// 标记每一个桶的最大值和最小值的数据结构
	public static class Entry {
		private Integer min, max;

		public Entry() {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
		}

		// 根据新的值调整桶的边界
		public final void adjust(int _num) {
			if (_num > max)
				max = _num;
			if (_num < min)
				min = _num;
		}
	}

	public static void main(String[] args) throws Exception {
		Integer[] raw = new Integer[] { 234, 2344, 1554, 1, 34, 2355, 63453,
				2341324, 345 };

		ArrayList<Integer> in = new ArrayList<Integer>();
		for (int idx = 0; idx < raw.length; ++idx) {
			in.add(raw[idx]);
		}
		System.out.print(new LinearMaxGapProblem().maxGap(in));
	}
}
