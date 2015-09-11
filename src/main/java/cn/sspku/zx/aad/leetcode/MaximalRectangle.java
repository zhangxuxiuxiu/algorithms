package cn.sspku.zx.aad.leetcode;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * 
 * @author zhangxu
 * 
 */
public class MaximalRectangle {

	private class Rectangle {
		int height, width, rCols, cRows;

		public Rectangle(int h, int w, int rc, int cr) {
			height = h;
			width = w;
			rCols = rc;
			cRows = cr;
		}

		int maxArea() {
			int max = height * width;
			if (rCols > max)
				max = rCols;
			if (cRows > max)
				max = cRows;
			return max;
		}

		@Override
		public String toString() {
			return "Rectangle [height=" + height + ", width=" + width
					+ ", rCols=" + rCols + ", cRows=" + cRows + "]";
		}

	}

	/**
	 * this algorithm fails in some cases ?
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		if (null == matrix || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int rows = matrix.length, cols = matrix[0].length;
		// area[i][j]代表以matrix[i][j]为右下角的矩形的最大面积
		Rectangle[][] areas = new Rectangle[rows][cols];

		// 初始化左上角第一个值
		areas[0][0] = matrix[0][0] == '1' ? new Rectangle(1, 1, 1, 1)
				: new Rectangle(0, 0, 0, 0);
		int maxArea = areas[0][0].width * areas[0][0].height;

		// 初始化第一行
		for (int c = 1; c < cols; ++c) {
			Rectangle area;
			// 当前值为零，不能作为矩形元素
			if (matrix[0][c] == '0')
				area = new Rectangle(0, 0, 0, 0);
			// 当前为一
			else
				area = new Rectangle(1, areas[0][c - 1].width + 1,
						areas[0][c - 1].width + 1, 1);

			areas[0][c] = area;
			if (area.maxArea() > maxArea)
				maxArea = area.maxArea();
			System.out.println(area);
		}

		// 初始化第一列
		for (int r = 1; r < rows; ++r) {
			Rectangle area;
			if (matrix[r][0] == '0')
				area = new Rectangle(0, 0, 0, 0);
			else
				area = new Rectangle(areas[r - 1][0].height + 1, 1, 1,
						areas[r - 1][0].height + 1);

			areas[r][0] = area;
			if (area.maxArea() > maxArea)
				maxArea = area.maxArea();
			System.out.println(area);
		}

		// 初始化其他值
		for (int r = 1; r < rows; ++r) {
			for (int c = 1; c < cols; ++c) {
				Rectangle area;
				// 当前值为零，不能作为矩形元素
				if (matrix[r][c] == '0')
					area = new Rectangle(0, 0, 0, 0);
				// 当前为一
				else {
					int w, h;
					if (areas[r - 1][c - 1].width != 0) {
						w = Math.min(areas[r - 1][c - 1].width,
								areas[r][c - 1].width) + 1;
						h = Math.min(areas[r - 1][c - 1].height,
								areas[r - 1][c].height) + 1;
					} else {
						if (areas[r][c - 1].width > areas[r - 1][c].height) {
							w = areas[r][c - 1].width + 1;
							h = 1;
						} else {
							h = areas[r - 1][c].height + 1;
							w = 1;
						}
					}

					area = new Rectangle(h, w, areas[r][c - 1].rCols + 1,
							areas[r - 1][c].cRows + 1);
				}

				areas[r][c] = area;
				if (area.maxArea() > maxArea)
					maxArea = area.maxArea();
				System.out.println(area);
			}
		}

		return maxArea;
	}

	public int maximalRectangle2(char[][] matrix) {
		if (null == matrix || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int height = matrix.length, width = matrix[0].length;
		int[][] colLens = new int[height][width];
		// 记录每一个cell以当前cell为底，以当前列为列，‘1’的高度
		for (int col = 0; col < width; ++col) {
			int r = 0, colHeight;
			while (r < height) {
				while (r < height && matrix[r][col] == '0')
					colLens[r++][col] = 0;
				colHeight = 0;
				while (r < height && matrix[r][col] == '1')
					colLens[r++][col] = ++colHeight;
			}
		}

		int maxArea = 0;
		Stack<Integer> indexStack = new Stack<Integer>();
		// 统计以所在行为底的矩形最大值
		for (int row = 0; row < height; ++row) {
			int c = 0, startIndex, area;
			while (c < width) {
				while (c < width && matrix[row][c] == '0')
					++c;
				startIndex = c;
				while (c < width && matrix[row][c] == '1') {
					if (indexStack.size() == 0
							|| colLens[row][indexStack.peek()] <= colLens[row][c])
						indexStack.push(c++);
					else {
						area = colLens[row][indexStack.pop()]
								* (indexStack.size() == 0 ? (c - startIndex)
										: (c - 1 - indexStack.peek()));
						maxArea = Math.max(maxArea, area);
					}
				}
				while (indexStack.size() > 0) {
					area = colLens[row][indexStack.pop()]
							* (indexStack.size() == 0 ? (c - startIndex)
									: (c - 1 - indexStack.peek()));
					maxArea = Math.max(maxArea, area);
				}
			}
		}

		return maxArea;
	}

	public static void main(String[] args) {
		MaximalRectangle mr = new MaximalRectangle();
		char[][] matrix = new char[][] {
				{ '1', '0', '1', '0', '1', '1', '1', '0' },
				{ '1', '1', '0', '1', '1', '0', '0', '0' },
				{ '1', '1', '1', '0', '0', '1', '0', '1' },
				{ '1', '0', '1', '1', '1', '1', '1', '0' },
				{ '0', '0', '0', '1', '1', '1', '1', '0' } };
		// char[][] matrix2 = new char[][] {
		// { '0', '0', '0', '1', '0', '1', '1', '1' },
		// { '0', '1', '1', '0', '0', '1', '0', '1' },
		// { '1', '0', '1', '1', '1', '1', '0', '1' },
		// { '0', '0', '0', '1', '0', '0', '0', '0' },
		// { '0', '0', '1', '0', '0', '0', '1', '0' },
		// { '1', '1', '1', '0', '0', '1', '1', '1' },
		// { '1', '0', '0', '1', '1', '0', '0', '1' },
		// { '0', '1', '0', '0', '1', '1', '0', '0' },
		// { '1', '0', '0', '1', '0', '0', '0', '0' } };
		// char[][] matrix3 = new char[][] { { '0', '1', '0', '0', '0' },
		// { '1', '0', '0', '1', '1' }, { '1', '0', '0', '1', '1' } };

		System.out.println(mr.maximalRectangle2(matrix));

	}
}
