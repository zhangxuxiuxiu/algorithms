package cn.sspku.zx.aad.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 * 
 * @author zhangxu
 * 
 */
public class TwoSum {

	/**
	 * You may assume that each input would have exactly one solution.
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		Map<Integer, Integer> hasher = new HashMap<Integer, Integer>();
		for (int r1 = 0; r1 < numbers.length; ++r1) {
			if (hasher.containsKey(target - numbers[r1]))
				return new int[] { hasher.get(target - numbers[r1]) + 1,r1 + 1};
			hasher.put(numbers[r1], r1);
		}

		return new int[2];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
