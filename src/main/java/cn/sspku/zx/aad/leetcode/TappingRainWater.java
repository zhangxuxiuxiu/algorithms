package cn.sspku.zx.aad.leetcode;


/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * 
 * @author zhangxu
 * 
 */
public class TappingRainWater {
	public int trap(int[] height) {
		if (null == height || height.length < 2)
			return 0;

		// 从左到右，左上坡计算最大值
		int lMaxIndex = 0, max = 0, shadows = 0;
		for (int idx = 1; idx < height.length; ++idx) {
			if (height[idx] >= height[lMaxIndex]) {
				max += height[lMaxIndex] * (idx - lMaxIndex - 1) - shadows;
				lMaxIndex = idx;
				shadows = 0;
			} else
				shadows += height[idx];
		}
		System.out.println(max + " " + lMaxIndex);
		// 从右到左，右下坡计算最大值
		int rMaxIndex = height.length - 1;
		shadows = 0;
		for (int idx = height.length - 2; idx > lMaxIndex; --idx) {
			if (height[idx] >= height[rMaxIndex]) {
				max += height[rMaxIndex] * (rMaxIndex - idx - 1) - shadows;
				rMaxIndex = idx;
				shadows = 0;
			} else
				shadows += height[idx];
		}

		// 处理左右两个最高峰之间的水槽
		if (rMaxIndex > lMaxIndex) {
			shadows = 0;
			for (int idx = lMaxIndex + 1; idx < rMaxIndex; ++idx)
				shadows += height[idx];
			max += height[rMaxIndex] * (rMaxIndex - lMaxIndex - 1) - shadows;
		}

		return max;
	}

	public static void main(String[] args) {
		TappingRainWater trw = new TappingRainWater();
		System.out.println(trw.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 3,
				2, 1, 2, 1 }));

	}
}
