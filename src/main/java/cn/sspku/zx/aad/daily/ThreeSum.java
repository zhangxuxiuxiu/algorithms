package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤
 * b ≤ c) The solution set must not contain duplicate triplets. For example,
 * given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is: (-1, 0, 1) (-1, -1, 2)
 * 
 * @author zhangxu
 * 
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> lsts = new ArrayList<List<Integer>>();
		if (null == num || num.length < 3)
			return lsts;

		Arrays.sort(num);
		int first = 0, second, three, incr;
		while (first < num.length - 2) {
			// 选择好第一个数据:同一个数据不能选择两次
			incr = 0;
			if (first > 0)
				while (first + incr < num.length - 2
						&& num[first + incr] == num[first - 1])
					++incr;
			first += incr;
			if (first < num.length - 2) {
				second = first + 1;
				while (second < num.length - 1) {
					// 选择第二个数据
					incr = 0;
					if (second > first + 1)
						while (second + incr < num.length - 1
								&& num[second + incr] == num[second - 1])
							++incr;
					second += incr;
					if (second < num.length - 1) {
						three = find(num, second + 1, num.length,
								-(num[first] + num[second]));
						if (-1 != three)
							lsts.add(Arrays.asList(num[first], num[second],
									num[three]));
					}
					++second;
				}
			}

			++first;
		}

		return lsts;
	}

	private int find(int[] num, int start, int end, int val) {
		if (num == null || start < 0 || end > num.length || start >= end
				|| val < num[start] || val > num[end - 1])
			return -1;

		int mid;
		while (start < end) {
			mid = (start + end) / 2;
			if (num[mid] == val)
				return mid;
			if (num[mid] > val)
				end = mid;
			else
				start = mid + 1;
		}

		return -1;
	}

	public static void main(String[] args) {
		Utils.Print(new ThreeSum().threeSum(new int[] { 1, 2, -2, -1 }));
	}
}
