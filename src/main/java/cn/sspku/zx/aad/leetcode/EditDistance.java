package cn.sspku.zx.aad.leetcode;

/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character b) Delete a character c) Replace a character
 * 
 * @author zhangxu
 * 
 */
public class EditDistance {
	/**
	 * We define D(i,j) • the edit distance between X[1..i] and Y[1..j] •
	 * i.e.,the first i characters of X and the first j characters of Y • The
	 * edit distance between X and Y is thus D(n,m)
	 * 
	 * Thus: D(i,j)=Min(D(i-1,j)+1,D(i,j-1)+1,D(i-1,j-1)+A[i]==B[j]?0:1)
	 * 
	 * @param word1
	 *            --->the original word
	 * @param word2
	 *            --->the word to be transformed to
	 * @return the minimum edit operation( Insert, Substitution Or Delete)
	 */
	public int minDistance(String word1, String word2) {
		if (word1 == null || word2 == null)
			return 0;
		int len1 = word1.length(), len2 = word2.length();
		int[][] table = new int[len1 + 1][len2 + 1];
		// initialize D(0,j)
		for (int j = 0; j < len2 + 1; ++j)
			table[0][j] = j;
		// initialize D(i,0)
		for (int i = 1; i < len1 + 1; ++i)
			table[i][0] = i;
		// calculate other situations
		for (int i = 1; i < len1 + 1; ++i)
			for (int j = 1; j < len2 + 1; ++j)
				table[i][j] = Math.min(
						Math.min(table[i][j - 1], table[i - 1][j]) + 1,
						table[i - 1][j - 1]
								+ (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1));

		return table[len1][len2];
	}

	public static void main(String[] args) {
		EditDistance ed=new EditDistance();
		String word1="abc",word2="bcd";
		System.out
				.println("The Min Edit Distance Between Word1 " + word1
						+ " and Word2 " + word2 + " is "
						+ ed.minDistance(word1, word2));
	}
}
