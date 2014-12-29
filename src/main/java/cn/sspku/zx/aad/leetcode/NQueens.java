package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chess board
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * For example, There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
 * 
 * ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
 * 
 * 
 * <<NQueen II>>
 * 
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of
 * distinct solutions.
 * 
 * @author zhangxu
 * 
 */
public class NQueens {

	public List<String[]> solveNQueens(int n) {
		List<String[]> solutions = new ArrayList<String[]>();
		if (n < 1)
			return solutions;

		int[][] boards = new int[n][n];
		int[] filledCols = new int[n];
		boolean[] cols = new boolean[n], LRCrosses = new boolean[2 * n - 1], RLCrosses = new boolean[2 * n - 1];

		solve(boards, filledCols, cols, LRCrosses, RLCrosses, 0, solutions);

		return solutions;
	}

	private boolean solve(int[][] boards, int[] filledCols, boolean[] cols,
			boolean[] lRCrosses, boolean[] rLCrosses, int step,
			List<String[]> solutions) {
		int n = filledCols.length;
		if (step == n) {
			solutions.add(imaginize(filledCols));
			return false;
		}

		boolean succeed = false;
		for (int c = 0; c < n; ++c) {
			if (!(cols[c] || lRCrosses[step - c + n - 1] || rLCrosses[step + c])) {
				cols[c] = lRCrosses[step - c + n - 1] = rLCrosses[step + c] = true;
				filledCols[step] = c;
				succeed=solve(boards, filledCols, cols, lRCrosses, rLCrosses,
						step + 1, solutions);
				//回溯，恢复原貌
				cols[c] = lRCrosses[step - c + n - 1] = rLCrosses[step + c] = false;
			}
		}
		
		return succeed;
	}

	private String[] imaginize(int[] filledCols) {
		String[] image = new String[filledCols.length];

		for (int row = 0; row < filledCols.length; ++row) {
			char[] rowImage = new char[filledCols.length];
			Arrays.fill(rowImage, '.');
			rowImage[filledCols[row]] = 'Q';
			image[row] = new String(rowImage);
		}

		return image;
	}

	public int totalNQueens(int n) {
		if (n < 1)
			return 0;

		return solveNQueens(n).size();
	}

	public static void main(String[] args) {
		NQueens q=new NQueens();
		System.out.println(q.totalNQueens(8));

	}
}
