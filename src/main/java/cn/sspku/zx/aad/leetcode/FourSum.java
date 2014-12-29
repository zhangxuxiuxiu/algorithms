package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 * 
 * Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order.
 * (ie, a ≤ b ≤ c ≤ d) The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 * A solution set is: (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2)
 * 
 * @author zhangxu
 * 
 */
public class FourSum {
	public List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> lsts = new ArrayList<List<Integer>>();
		if (null == num || num.length < 4)
			return lsts;

		Arrays.sort(num);
		int first = 0, second, three, four, incr;
		while (first < num.length - 3) {
			// 选择好第一个数据:同一个数据不能选择相同的
			incr = 0;
			if (first > 0)
				while (first + incr < num.length - 3
						&& num[first + incr] == num[first - 1])
					++incr;
			first += incr;
			if (first < num.length - 3) {
				second = first + 1;
				while (second < num.length - 2) {
					// 选择第二个数据
					incr = 0;
					if (second > first + 1)
						while (second + incr < num.length - 2
								&& num[second + incr] == num[second - 1])
							++incr;
					second += incr;
					if (second < num.length - 2) {
						three = second + 1;
						while (three < num.length - 1) {
							// 选择第二个数据
							incr = 0;
							if (three > second + 1)
								while (three + incr < num.length - 1
										&& num[three + incr] == num[three - 1])
									++incr;
							three += incr;
							if (three < num.length - 1) {
								four = find(
										num,
										three + 1,
										num.length,
										target
												- (num[first] + num[second] + num[three]));
								if (-1 != four)
									lsts.add(Arrays.asList(num[first],
											num[second], num[three], num[four]));
							}
							++three;
						}
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
		Utils.Print(new FourSum().fourSum(new int[] {1, 0 ,-1 ,0 ,-2 ,2}, 0));
	}
}
