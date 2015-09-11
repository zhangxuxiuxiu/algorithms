package cn.sspku.zx.aad.InterviewQs;

import java.util.Arrays;

/**
 * Given a value N, if we want to make change for N cents, and we have infinite
 * supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we
 * make the change? The order of coins doesn’t matter.
 * 
 * @author zhangxu
 * 
 */
public class CoinChange {
	/**
	 * @solution:To count total number solutions, we can divide all set
	 *              solutions in two sets. 1) Solutions that do not contain mth
	 *              coin (or Sm). 2) Solutions that contain at least one Sm. Let
	 *              count(S[], m, n) be the function to count the number of
	 *              solutions, then it can be written as sum of count(S[], m-1,
	 *              n) and count(S[], m, n-Sm).
	 * @param args
	 */
	int count(int[] s, int total) {
		//base corner
		if (null == s || total < 1)
			return 0;
		
		//construct the result from bottom
		int[] table = new int[total + 1];
		Arrays.fill(table, 0);

		// Base case (If given value is 0)
		table[0] = 1;//for every s[i] subproblem, the result is 1

		// Pick all coins one by one and update the table[] values
		// after the index greater than or equal to the value of the
		// picked coin
		for (int i = 0; i < s.length; i++)
			for (int j = s[i]; j <= total; j++)
				table[j] += table[j - s[i]];

		return table[total];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
