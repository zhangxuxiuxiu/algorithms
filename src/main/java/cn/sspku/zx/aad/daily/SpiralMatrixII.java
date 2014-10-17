package cn.sspku.zx.aad.daily;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n2 in spiral order.
 * 
 * For example, Given n = 3,
 * 
 * You should return the following matrix: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5
 * ] ]
 * 
 * @author zhangxu
 * 
 */
public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
		if (n < 0)
			return new int[0][0];

		int[][] matrix = new int[n][n];

		draw(matrix, n, 1, 1);

		return matrix;
	}

	private void draw(int[][] matrix, int n, int width, int val) {
		if (width <= (n + 1) / 2) {
			int row = width - 1, col = n - width;
			for (int c = width - 1; c < n - width + 1; ++c)
				matrix[row][c] = val++;
			for (int r = width; r < n - width + 1; ++r)
				matrix[r][col] = val++;

			row = n - width;
			col = width - 1;
			for (int c = n - width - 1; c > width - 2; --c)
				matrix[row][c] = val++;
			for (int r = n - width - 1; r > width - 1; --r)
				matrix[r][col] = val++;

			draw(matrix, n, width + 1, val);
		}
	}

	public static void main(String[] args) {
		SpiralMatrixII sm = new SpiralMatrixII();
		int n=3;
		int[][] matrix = sm.generateMatrix(n);
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < n; ++c)
				System.out.printf("%5d",matrix[r][c]);
			System.out.println();
		}
	}
}
