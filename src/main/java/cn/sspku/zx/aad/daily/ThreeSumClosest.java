package cn.sspku.zx.aad.daily;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers. You
 * may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * @author zhangxu
 * 
 */
public class ThreeSumClosest {
	public int threeSumClosest(int[] num, int target) {
		int closestSum = 0;

		if (null != num && num.length > 2) {
			for (int idx = 0; idx < 3; ++idx)
				closestSum += num[idx];
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
							three = find(num, second + 1, num.length, target
									- (num[first] + num[second]));
							if (three != -1) {
								if (three == num.length) {
									if (Math.abs(target
											- (num[first] + num[second] + num[three - 1])) < Math
											.abs(target - closestSum))
										closestSum = num[first] + num[second]
												+ num[three - 1];
								} else if (target
										- (num[first] + num[second] + num[three]) == 0)
									return target;
								else {
									if (Math.abs(target
											- (num[first] + num[second] + num[three])) < Math
											.abs(target - closestSum))
										closestSum = num[first] + num[second]
												+ num[three];
									if (three > second + 1) {
										if (Math.abs(target
												- (num[first] + num[second] + num[three - 1])) < Math
												.abs(target - closestSum))
											closestSum = num[first]
													+ num[second]
													+ num[three - 1];
									}
								}
							}
						}
						++second;
					}
				}
				++first;
			}
		}

		return closestSum;
	}

	private int find(int[] num, int start, int end, int val) {
		if (num == null || start < 0 || end > num.length || start >= end)
			return -1;
		if (val < num[start])
			return start;
		if (val > num[end - 1])
			return end;

		int mid;
		while (start < end) {
			mid = (start + end) / 2;
			if (num[mid] < val)
				start = mid + 1;
			else if (num[mid] == val)
				return mid;
			else {
				if (mid > start) {
					if (num[mid - 1] < val)
						return mid;
					if (num[mid - 1] == val)
						return mid - 1;
					end = mid;
				} else
					return mid;
			}
		}

		return start;
	}

	// Time Limit Exceeding
	public int threeSumClosest2(int[] num, int target) {
		int closestSum = 0, sum;

		if (null != num && num.length > 2) {
			for (int idx = 0; idx < 3; ++idx)
				closestSum += num[idx];

			for (int r1 = 0; r1 < num.length - 2; ++r1)
				for (int r2 = r1 + 1; r2 < num.length - 1; ++r2)
					for (int r3 = r2 + 1; r3 < num.length; ++r3) {
						sum = num[r1] + num[r2] + num[r3];
						if (Math.abs(target - closestSum) > Math.abs(target
								- sum))
							closestSum = sum;
					}
		}

		return closestSum;
	}

	public static void main(String[] args) {
		Utils.Print(""
				+ new ThreeSumClosest().threeSumClosest2(new int[] { -1, 2, 1,
						-4 }, 1));
	}
}
