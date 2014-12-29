package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the
 * following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation
 * sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * @author zhangxu
 * 
 */
public class PermutationSequence {
	public String getPermutation(int n, int k) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int[] ns = new int[n];
		int base = 1;
		for (int idx = 0; idx < n; ++idx) {
			digits.add(idx + 1);
			base *= (idx + 1);
			ns[idx] = base;
		}

		int nBits = n - 2, order;
		String ret = "";
		while (k > 1) {
			order = (k - 1) / ns[nBits];
			k -= order * ns[nBits];
			ret += digits.get(order);
			digits.remove(order);
			--nBits;
		}
		for (int idx = 0; idx < digits.size(); ++idx)
			ret += digits.get(idx);

		return ret;
	}

	public static void main(String[] args) {
		PermutationSequence ps = new PermutationSequence();
		System.out.println(ps.getPermutation(4, 23));
	}
}
