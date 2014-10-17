package cn.sspku.zx.aad.daily;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram
 * 
 * @author zhangxu
 * 
 */
public class LargestRectangleInHistogram {

	public int largestRectangleArea(int[] height) {
		if (null == height || height.length == 0)
			return 0;

		int ret = 0, area, left, right, _left, _right;
		for (int idx = 0; idx < height.length; ++idx) {
			left = idx;
			right = idx + 1;
			// 寻找一个上升的坡
			while (right < height.length && height[right - 1] <= height[right])
				++right;
			// 处理坡度中最小的值
			_left = left-1;
			while (_left >= 0 && height[_left] >= height[left])
				--_left;
			_right = right;
			while (_right < height.length && height[_right] >= height[left])
				++_right;
			area = height[left] * (_right - _left-1);
			System.out.println(area);
			if (area > ret)
				ret = area;
			// 处理坡度中的其他节点
			if (right - left > 1) {
				for (int tpr = left + 1; tpr < right; ++tpr) {
					area = height[tpr] * (right - tpr);
					if (area > ret)
						ret = area;
				}
			}

			idx=right-1;
		}

		return ret;
	}

	public static void main(String[] args) {
		int[] height=new int[]{1,3,4,3,5};
		System.out.println(new LargestRectangleInHistogram().largestRectangleArea(height));
	}

}
