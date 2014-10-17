package cn.sspku.zx.aad.daily;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * @author zhangxu
 * 
 */
public class ImageRotator {
	public void rotate(int[][] matrix) {
		if (null == matrix || matrix.length < 2)
			return;

		int length = matrix.length - 1,totalLength=length, outerWidth = 0;
		int[] array = new int[length];
		while (length > 0) {
			// 将第一行放到数组当中
			for (int idx = 0; idx < length; ++idx) {
				array[idx] = matrix[outerWidth][idx + outerWidth];
			}
			// 将第一列放到第一行中
			for (int idx = 0; idx < length; ++idx) {
				matrix[outerWidth][idx + outerWidth] = matrix[totalLength-idx-outerWidth][outerWidth];
			}
			// 将最后一行放到第一列
			for (int idx = 0; idx < length; ++idx) {
				matrix[idx + outerWidth + 1][outerWidth] = matrix[totalLength
						- outerWidth][idx + outerWidth + 1];
			}
			// 将最后一列放到最后一行中
			for (int idx = 0; idx < length; ++idx) {
				matrix[totalLength - outerWidth][idx + outerWidth + 1] = matrix[totalLength-idx-outerWidth-1]
						[totalLength - outerWidth];
			}
			// 将第一行放到最后一列中
			for (int idx = 0; idx < length; ++idx) {
				matrix[idx + outerWidth][totalLength - outerWidth] = array[idx];
			}

			++outerWidth;
			length -= 2;
		}
	}

	public static void print(int[][] matrix) {
		int length = matrix.length;

		for (int row = 0; row < length; ++row) {
			for (int col = 0; col < length; ++col) {
				System.out.printf("%5d",matrix[row][col]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		int[][] matrix=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		print(matrix);
		new ImageRotator().rotate(matrix);
		print(matrix);
	}
}
