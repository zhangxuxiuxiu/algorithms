package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example, Given the following matrix:
 * 
 * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return
 * [1,2,3,6,9,8,7,4,5].
 * 
 * @author zhangxu
 * 
 */
public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> lst = new ArrayList<Integer>();
		if (null == matrix || matrix.length == 0)
			return lst;

		int rows = matrix.length, cols = matrix[0].length, width = 0, height = 0;

		while (true) {
			// 如果没有需要处理的行数，则直接返回
			if (2 * height == rows || 2 * width == cols)
				return lst;
			// 只剩下一行需要处理
			if (2 * height == rows - 1) {
				for (int c = width; c < cols - width; ++c)
					lst.add(matrix[height][c]);

				return lst;
			}
			// 只有一列需要处理
			if (2 * width == cols - 1) {
				for (int r = height; r < rows - height; ++r)
					lst.add(matrix[r][width]);

				return lst;
			}

			// 还有许多行列需要处理:从上左到上右，从上右到下右，从下右到下左，从下左到上左
			// 从上左到上右
			for (int c = width; c < cols - width - 1; ++c)
				lst.add(matrix[height][c]);
			// 从上右到下右
			for (int r = height; r < rows - height - 1; ++r)
				lst.add(matrix[r][cols - width - 1]);
			// 从下右到下左
			for (int c = cols - width - 1; c > width; --c)
				lst.add(matrix[rows - height - 1][c]);
			// 从下左到上左
			for (int r = rows - height - 1; r > height; --r)
				lst.add(matrix[r][width]);

			++height;
			++width;
		}
	}

	public static void main(String[] args) {
		SpiralMatrix sm=new SpiralMatrix();
		List<Integer> lst=sm.spiralOrder(new int[][]{{1,2},{3,4},{5,6}});
		
		Iterator<Integer> it=lst.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next());
		}
	}
}
