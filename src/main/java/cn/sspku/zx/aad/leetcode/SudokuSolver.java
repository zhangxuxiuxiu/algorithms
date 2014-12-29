package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 * 
 * 
 * A sudoku puzzle...
 * 
 * 
 * @author zhangxu
 * 
 */
public class SudokuSolver {
	/**
	 * 基本思路：记录每一个cell可以填哪些数字，以及可以填写的数字的个数，个数越少越优先填写
	 * 
	 * @param board
	 */

	public void solveSudoku(char[][] board) {
		solve(board, 0);
		print(board);
	}

	private boolean solve(char[][] board, int ind) {
		if (ind == 81)
			return true; // solved and it's correct

		int row = ind / 9;
		int col = ind % 9;

		// Advance forward on cells that are prefilled
		if (board[row][col] != '.')
			return solve(board, ind + 1);

		else {
			// we are positioned on first something we need to fill in.
			// Try all possibilities

			for (int i = 1; i <= 9; i++) {
				board[row][col] = Character.forDigit(i, 10);
				if (isValidSudoku(board, row, col)) {
					// Don't backtrack if this choice leads
					// to a final solution since we need to modify
					// the original array
					if (solve(board, ind + 1))
						return true;
				}
				board[row][col] = '.'; // unmake move
			}
		}

		// no solution
		return false;
	}

	public boolean isValidSudoku(char[][] board, int row, int col) {
		int[] flags = new int[9];
		// 处理每一行

		initFlags(flags);
		for (int c = 0; c < 9; ++c)
			if (board[row][c] != '.')
				if (flags[board[row][c] - '1'] == 1)
					return false;
				else
					flags[board[row][c] - '1'] = 1;

		// 处理每一列

		initFlags(flags);
		for (int r = 0; r < 9; ++r)
			if (board[r][col] != '.')
				if (flags[board[r][col] - '1'] == 1)
					return false;
				else
					flags[board[r][col] - '1'] = 1;

		// 处理每一子九宫格

		initFlags(flags);
		for (int idx = 0; idx < 9; ++idx)
			if (board[row / 3 * 3 + idx / 3][col / 3 * 3 + idx % 3] != '.')
				if (flags[board[row / 3 * 3 + idx / 3][col / 3 * 3 + idx % 3] - '1'] == 1)
					return false;
				else
					flags[board[row / 3 * 3 + idx / 3][col / 3 * 3 + idx % 3] - '1'] = 1;

		return true;
	}

	void print(char[][] board) {
		for (int row = 0; row < 9; ++row) {
			for (int col = 0; col < 9; ++col)
				System.out.printf("%5c", board[row][col]);
			System.out.println();
		}

	}

	private void initFlags(int[] flags) {
		Arrays.fill(flags, 0);
	}

	public static void main(String[] args) {
		SudokuSolver ss = new SudokuSolver();
		/*char[][] board = new char[][] {
				{ '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		char[][] board2 = new char[][] {
				{ '.', '.', '9', '7', '4', '8', '.', '.', '.' },
				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '2', '.', '1', '.', '9', '.', '.', '.' },
				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' },
				{ '.', '6', '4', '.', '1', '.', '5', '9', '.' },
				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' },
				{ '.', '.', '.', '8', '.', '3', '.', '2', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' },
				{ '.', '.', '.', '2', '7', '5', '9', '.', '.' } };*/
		char[][] board3 = new char[][] {
				{ '.', '.', '.', '2', '.', '.', '.', '6', '3' },
				{ '3', '.', '.', '.', '.', '5', '4', '.', '1' },
				{ '.', '.', '1', '.', '.', '3', '9', '8', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '9', '.' },
				{ '.', '.', '.', '5', '3', '8', '.', '.', '.' },
				{ '.', '3', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '2', '6', '3', '.', '.', '5', '.', '.' },
				{ '5', '.', '3', '7', '.', '.', '.', '.', '8' },
				{ '4', '7', '.', '.', '.', '1', '.', '.', '.' } };

		ss.solveSudoku(board3);

		// failed
		// cases:["..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."]
		// ["...2...63","3....54.1","..1..398.",".......9.","...538...",".3.......",".263..5..","5.37....8","47...1..."]
	}
}
