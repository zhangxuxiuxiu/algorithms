package cn.sspku.zx.aad.daily;

import java.util.Stack;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by
 * 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * For example, X X X X X O O X X X O X X O X X After running your function, the
 * board should be:
 * 
 * X X X X X X X X X X X X X O X X
 * 
 * @author zhangxu
 * 
 */
public class SurroundedRegions {
	private static final char oMark = 'Z';

	/**
	 * 所谓‘O’被'X'包围，指的是在‘O’的左右上下要么被‘X’占领，要么被那些周围被‘X’占领的‘O’所占领
	 * 一个‘O’不被占领，就只有一种情况，那便是该‘O’所在的‘O’区域至少有一个‘O’是处在整个board的最外层，
	 * 即，整个‘O’区域的被包围突破口，从而没有被包围。
	 * 
	 * @param board
	 */
	public void solve(char[][] board) {
		int height = board.length, width = 0;
		if (height > 0)
			width = board[0].length;
		else
			return;
		if (null == board || height < 3 || width < 3)
			return;

		// 在board的最外面两行两列找寻‘O’
		// 首行
		for (int col = 0; col < width; ++col)
			if (board[0][col] == 'O')
				extend(board, 0, col);

		// 末行
		for (int col = 0; col < width; ++col)
			if (board[height - 1][col] == 'O')
				extend(board, height - 1, col);

		// 首列
		for (int row = 1; row < height - 1; ++row)
			if (board[row][0] == 'O')
				extend(board, row, 0);

		// 末列
		for (int row = 1; row < height - 1; ++row)
			if (board[row][width - 1] == 'O')
				extend(board, row, width - 1);

		// 将board中确定没有被包围的‘O’重新标记为‘O’，其他的都标记为‘X’
		for (int r = 0; r < height; ++r)
			for (int c = 0; c < width; ++c)
				if (board[r][c] == oMark)
					board[r][c] = 'O';
				else
					board[r][c] = 'X';
	}

	// TLE
	// private void extend2(char[][] board, int row, int col) {
	// if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
	// return;
	// if (board[row][col] == 'O') {
	// board[row][col] = oMark;
	// extend(board, row, col - 1);
	// extend(board, row, col + 1);
	// extend(board, row + 1, col);
	// extend(board, row - 1, col);
	// }
	// }
	
	private void extend(char[][] board, int row, int col) {
		Stack<Integer> rowStack = new Stack<Integer>(), colStack = new Stack<Integer>();
		rowStack.push(row);
		colStack.push(col);
		while (rowStack.size() > 0) {
			row = rowStack.pop();
			col = colStack.pop();
			if (row >= 0 && row < board.length && col >= 0
					&& col < board[0].length && board[row][col] == 'O') {
				board[row][col] = oMark;
				rowStack.push(row);
				colStack.push(col - 1);
				rowStack.push(row);
				colStack.push(col + 1);
				rowStack.push(row + 1);
				colStack.push(col);
				rowStack.push(row - 1);
				colStack.push(col);
			}
		}
	}

	static void print(char[][] board) {
		for (int r = 0; r < board.length; ++r) {
			for (int c = 0; c < board[r].length; ++c)
				System.out.printf("%5c", board[r][c]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		SurroundedRegions sr = new SurroundedRegions();
		char[][] board = new char[][] { { 'X', 'X', 'X', 'X' },
				{ 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };

		print(board);
		sr.solve(board);
		System.out.println();
		print(board);
	}
}
