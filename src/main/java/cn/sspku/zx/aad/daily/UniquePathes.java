package cn.sspku.zx.aad.daily;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * @author zhangxu
 * 
 */
public class UniquePathes {
	public int uniquePaths(int m, int n) {
		if (m < 1 || n < 1)
			return 0;
		if (m == 1 || n == 1)
			return 1;

		int[][] cells = new int[m][n];
		for (int i = 0; i < m; ++i)
			cells[i][0] = 1;
		for (int i = 1; i < n; ++i)
			cells[0][i] = 1;
		for (int r = 1; r < m; ++r)
			for (int c = 1; c < n; ++c)
				cells[r][c] = cells[r - 1][c] + cells[r][c - 1];

		return cells[m - 1][n - 1];
	}

	/**
	 * Follow up for "Unique Paths":
	 * 
	 * Now consider if some obstacles are added to the grids. How many unique
	 * paths would there be?
	 * 
	 * An obstacle and empty space is marked as 1 and 0 respectively in the
	 * grid.
	 * 
	 * For example, There is one obstacle in the middle of a 3x3 grid as
	 * illustrated below.
	 * 
	 * [ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2.
	 * 
	 * 
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (null == obstacleGrid || obstacleGrid.length == 0
				|| obstacleGrid[0][0] == 1)
			return 0;

		int rows = obstacleGrid.length, cols = obstacleGrid[0].length;

		// 处理第一列的情况
		int r;
		for (r = 0; r < rows; ++r)
			if (obstacleGrid[r][0] == 0)
				obstacleGrid[r][0] = 1;
			else
				break;
		while (r < rows)
			obstacleGrid[r++][0] = 0;

		// 处理第一行的情况
		int c;
		for (c = 1; c < cols; ++c)
			if (obstacleGrid[0][c] == 0)
				obstacleGrid[0][c] = 1;
			else
				break;
		while (c < cols)
			obstacleGrid[0][c++] = 0;

		for (r = 1; r < rows; ++r)
			for (c = 1; c < cols; ++c)
				if (obstacleGrid[r][c] == 1)
					obstacleGrid[r][c] = 0;
				else
					obstacleGrid[r][c] = obstacleGrid[r - 1][c]
							+ obstacleGrid[r][c - 1];

		return obstacleGrid[rows - 1][cols - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
