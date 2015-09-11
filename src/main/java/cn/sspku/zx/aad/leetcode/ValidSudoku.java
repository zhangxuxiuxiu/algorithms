package cn.sspku.zx.aad.leetcode;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with
 * the character '.'.
 * 
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 * 
 * @author zhangxu
 * 
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		int[] flags = new int[9];
		// 处理每一行
		for (int r = 0; r < 9; ++r) {
			initFlags(flags);
			for (int c = 0; c < 9; ++c)
				if (board[r][c] != '.')
					if (flags[board[r][c] - '1'] == 1)
						return false;
					else
						flags[board[r][c] - '1'] = 1;
		}

		// 处理每一列
		for (int c = 0; c < 9; ++c) {
			initFlags(flags);
			for (int r = 0; r < 9; ++r)
				if (board[r][c] != '.')
					if (flags[board[r][c] - '1'] == 1)
						return false;
					else
						flags[board[r][c] - '1'] = 1;
		}

		// 处理每一子九宫格
		for (int cells = 0; cells < 9; ++cells) {
			initFlags(flags);
			int rows = cells / 3, cols = cells % 3;
			for (int r = 0; r < 3; ++r)
				for (int c = 0; c < 3; ++c)
					if (board[rows * 3 + r][cols * 3 + c] != '.')
						if (flags[board[rows * 3 + r][cols * 3 + c] - '1'] == 1)
							return false;
						else
							flags[board[rows * 3 + r][cols * 3 + c] - '1'] = 1;
		}

		return true;
	}

	private void initFlags(int[] flags) {
		for (int idx = 0; idx < flags.length; ++idx)
			flags[idx] = 0;
	}

	public static void main(String[] args) {
		

	}
}
