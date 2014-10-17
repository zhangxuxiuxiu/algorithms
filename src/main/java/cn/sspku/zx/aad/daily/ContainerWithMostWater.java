package cn.sspku.zx.aad.daily;


/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 * 
 * @author zhangxu
 * 
 */
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		if (null == height || height.length < 2)
			return 0;

		// 从左到右，左上坡计算最大值
		int lMaxIndex = 0, max = 0, capacity;
		for (int idx = 1; idx < height.length; ++idx) {
			if (height[idx] > height[lMaxIndex]) {
				lMaxIndex = idx;
			}
		}

		// 从右到左，右下坡计算最大值
		int rMaxIndex = height.length - 1;
		for (int idx = height.length - 1; idx > lMaxIndex; --idx) {
			if (height[idx] > height[rMaxIndex]) {
				capacity = height[rMaxIndex] * (rMaxIndex - idx + 1);
				if (capacity > max) {
					max = capacity;
				}

				rMaxIndex = idx;
			}
		}

		return max;
	}

	public static void main(String[] args) {

	}
}
