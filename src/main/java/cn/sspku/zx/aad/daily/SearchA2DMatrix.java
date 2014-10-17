package cn.sspku.zx.aad.daily;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return
 * true.
 * 
 * @author zhangxu
 * 
 */
public class SearchA2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (null == matrix || matrix.length == 0)
			return false;

		int upper = 0, low = matrix.length - 1, mid = 0;
		while (upper <= low) {
			mid = (upper + low) / 2;
			if (target < matrix[mid][0])
				low = mid - 1;
			// 如果找到target属于mid行，则退出
			else {
				if (mid < matrix.length - 1 && target >= matrix[mid + 1][0])
					upper = mid + 1;
				else
					break;
			}
		}

		int left = 0, right = matrix[0].length-1, mCol;
		while (left <= right) {
			mCol = (left + right) / 2;
			if (matrix[mid][mCol] == target)
				return true;
			if (target < matrix[mid][mCol])
				right = mCol - 1;
			else
				left = mCol + 1;
		}

		return false;
	}
}
