package cn.sspku.zx.aad.leetcode;

import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle [ [2], [3,4], [6,5,7], [4,1,8,3] ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note: Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 * 
 * @author zhangxu
 * 
 */
public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		if (null == triangle || triangle.size() == 0)
			return 0;

		int length = triangle.size();
		Integer[] maxes = new Integer[length];
		//将最后一个列表的数据放到数组当中
		List<Integer> aList = triangle.get(length - 1);
		maxes = aList.toArray(maxes);

		//从后往前对每一个列表的数据进行处理
		for (int idx = length - 2; idx >= 0; --idx) {
			aList = triangle.get(idx);
			for (int idx2 = 0; idx2 < aList.size(); ++idx2)
				maxes[idx2] = aList.get(idx2)
						+ Math.min(maxes[idx2], maxes[idx2 + 1]);
		}

		return maxes[0];
	}
}
