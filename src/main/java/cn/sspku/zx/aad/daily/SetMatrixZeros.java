package cn.sspku.zx.aad.daily;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * @author zhangxu
 * 
 */
public class SetMatrixZeros {
	public void setZeroes(int[][] matrix) {
		if (null == matrix || matrix.length == 0)
			return;
		int rows = matrix.length, cols = matrix[0].length;

		boolean[] markedRows = new boolean[rows], markedCols = new boolean[cols];

		for (int r = 0; r < rows; ++r)
			for (int c = 0; c < cols; ++c)
				if (matrix[r][c] == 0) {
					markedRows[r] = true;
					markedCols[c] = true;
				}
		
		for(int r=0;r<rows;++r)
			if(markedRows[r])
				for(int c=0;c<cols;++c)
					matrix[r][c]=0;
		
		for(int c=0;c<cols;++c)
			if(markedCols[c])
			    for(int r=0;r<rows;++r)
					matrix[r][c]=0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
