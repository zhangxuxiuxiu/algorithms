package cn.sspku.zx.aad.daily;

import java.util.Stack;

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
		// Create an empty stack. The stack holds indexes of hist[] array
		// The bars stored in stack are always in increasing order of their
		// heights.
		Stack<Integer> s = new Stack<Integer>();

		int max_area = 0; // Initalize max area
		int tp; // To store top of stack
		int area_with_top; // To store area with top bar as the smallest bar

		// Run through all bars of given histogram
		int i = 0;
		while (i < height.length) {
			// If this bar is higher than the bar on top stack, push it to stack
			if (s.empty() || height[s.peek()] <= height[i])
				s.push(i++);

			// If this bar is lower than top of stack, then calculate area of
			// rectangle
			// with stack top as the smallest (or minimum height) bar. 'i' is
			// 'right index' for the top and element before top in stack is
			// 'left index'
			else {
				tp = s.pop(); // store the top index

				// Calculate the area with hist[tp] stack as smallest bar
				area_with_top = height[tp] * (s.empty() ? i : i - s.peek() - 1);

				// update max area, if needed
				if (max_area < area_with_top)
					max_area = area_with_top;
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (!s.empty()) {
			tp = s.pop();
			area_with_top = height[tp] * (s.empty() ? i : i - s.peek() - 1);

			if (max_area < area_with_top)
				max_area = area_with_top;
		}

		return max_area;
	}

	public static void main(String[] args) {
		int[] height = new int[] { 1, 3, 4, 3, 5 };
		System.out.println(new LargestRectangleInHistogram()
				.largestRectangleArea(height));
	}

}
