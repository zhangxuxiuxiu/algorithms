package cn.sspku.zx.aad.leetcode;

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
	/**
	 * 选定最左边和最右边的两条线，如果有更大的水槽，一定是较短的那一边到中间扩展过程中相对自身更高的线段替代
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		if (null == height || height.length < 2)
			return 0;

		int left = 0, right = height.length - 1, max = (right - left)
				* Math.min(height[left], height[right]), seg;
		while (left < right) {
			if (height[left] < height[right]) {
				seg = 1;
				while (left + seg < right && height[left + seg] <= height[left])
					++seg;
				if (left + seg >= right)
					return max;
				left += seg;
				max = Math.max(max,
						(right - left) * Math.min(height[left], height[right]));
			} else {
				seg = 1;
				while (right - seg > left
						&& height[right - seg] <= height[right])
					++seg;
				if (right - seg <= left)
					return max;
				right -= seg;
				max = Math.max(max,
						(right - left) * Math.min(height[left], height[right]));
			}
		}

		return max;
	}

	public static void main(String[] args) {
		Utils.Print(""
				+ new ContainerWithMostWater().maxArea(new int[] { 1, 2, 4, 3,
						5 }));

	}
}
