package cn.sspku.zx.aad.leetcode;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ["ABCE"], ["SFCS"], ["ADEE"] ] word = "ABCCED", -> returns true, word =
 * "SEE", -> returns true, word = "ABCB", -> returns false.
 * 
 * @author zhangxu
 * 
 */
public class WordSearcher {

	private boolean[][] flags = null;
	public static int[][] rules = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 },
			{ 0, -1 } };

	private char[][] board;
	private String word;
	private int length, width;

	private static WordSearcher ws=null;
	
	private WordSearcher(char[][] _board, String _word) {
		board = _board;
		word = _word;
		int length = board.length, width = board[0].length;
		initFlags(length, width);
	}

	public static boolean exist(char[][] board, String word) {
		if (null == word || word.length() == 0 || null == board
				|| board.length == 0)
			return false;
		
		if(null==ws) ws=new WordSearcher(board,word);
		
		return ws.checkExistence();
	}

	private boolean checkExistence()
	{
		for (int i = 0; i < length; ++i)
			for (int j = 0; j < width; ++j) {
				if (board[i][j] == word.charAt(0)) {
					if (test( i, j, 0))
						return true;
				}
			}

		return false;
	}
	
	private boolean test(int row, int col,int step) {
		if (row < 0 || col < 0 || row >= length || col >= width
				|| flags[row][col] || step >= word.length())
			return false;

		if (board[row][col] == word.charAt(step)) {
			flags[row][col] = true;
			if (step == word.length() - 1)
				return true;
			for (int i = 0; i < rules.length; ++i) {
				if (test( row + rules[i][0], col + rules[i][1],  step + 1))
					return true;
			}
		}
		//如果此路不通，则恢复标志位
		flags[row][col] = false;

		return false;
	}

	private void initFlags(int length, int width) {
		if (null == flags) {
			flags = new boolean[length][width];
		}
		for (int i = 0; i < length; ++i) {
			for (int j = 0; j < width; ++j)
				flags[i][j] = false;
		}
	}

	public static void main(String[] args) {
		WordSearcher.exist(new char[2][3], "");
	}

}
