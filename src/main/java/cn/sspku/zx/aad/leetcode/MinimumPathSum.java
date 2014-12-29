package cn.sspku.zx.aad.leetcode;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * @author zhangxu
 * 
 */
public class MinimumPathSum {
	int minSum = Integer.MAX_VALUE;

	public int minPathSum(int[][] grid) {
		if (null == grid)
			return 0;

		findMinPath(grid, grid.length + grid[0].length - 2, 0, 0, 0);

		return minSum;
	}

	private void findMinPath(int[][] grid, int remainingStep, int row, int col,
			int sum) {
		sum += grid[row][col];

		// 剪枝
		if (sum >= minSum)
			return;

		if (0 == remainingStep) {
			if (sum < minSum)
				minSum = sum;
			return;
		}

		if (row < grid.length - 1) {
			if (col >= grid[0].length - 1)
				findMinPath(grid, remainingStep - 1, row + 1, col, sum);
			else {
				if (grid[row + 1][col] < grid[row][col + 1]) {
					findMinPath(grid, remainingStep - 1, row + 1, col, sum);
					findMinPath(grid, remainingStep - 1, row, col + 1, sum);
				} else {
					findMinPath(grid, remainingStep - 1, row, col + 1, sum);
					findMinPath(grid, remainingStep - 1, row + 1, col, sum);
				}
			}
		} else if (col < grid[0].length - 1)
			findMinPath(grid, remainingStep - 1, row, col + 1, sum);

		// 回溯，消除影响
		sum -= grid[row][col];
	}

	public int minPathSum2(int[][] grid) {
		if (null == grid)
			return 0;
		
		int rows=grid.length,cols=grid[0].length;
		
		int[][] sums=new int[rows][cols];
		sums[0][0]=grid[0][0];
		//初始化第一行
		for(int r=1;r<cols;++r)
		{
			sums[0][r]=sums[0][r-1]+grid[0][r];
		}
		//初始化第一列
		for(int c=1;c<rows;++c)
		{
			sums[c][0]=sums[c-1][0]+grid[c][0];
		}
		//计算其他格子的和
		for(int r=1;r<rows;++r)
			for(int c=1;c<cols;++c)
			{
				sums[r][c]=grid[r][c]+Math.min(sums[r][c-1], sums[r-1][c]);
			}
		
		return sums[rows-1][cols-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
