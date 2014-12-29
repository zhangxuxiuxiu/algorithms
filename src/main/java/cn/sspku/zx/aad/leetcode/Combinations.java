package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 * 
 * For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 * @author zhangxu
 * 
 */
public class Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combines = new ArrayList<List<Integer>>();
		if (n < 0 || k < 0 || k > n) {
			combines.add(null);
			return combines;
		}

		eachCombine(combines, new int[k], n, k, 0,1);

		return combines;
	}

	void eachCombine(List<List<Integer>> combines, int[] list, int n, int k,
			int idx,int start) {
		if (idx == k) {
			List<Integer> combine = new ArrayList<Integer>();
			for (int i = 0; i < list.length; ++i)
				combine.add(list[i]);
			combines.add(combine);
			return;
		}

		for (int p = start; p < n - k + idx + 2; ++p) {
			list[idx] = p;
			eachCombine(combines, list, n, k, idx + 1,p+1);
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> combines = new Combinations().combine(4, 2);

		Iterator<List<Integer>> listIt = combines.iterator();
		List<Integer> list;
		while (listIt.hasNext()) {
			list = listIt.next();
			Iterator<Integer> it = list.iterator();
			while (it.hasNext()) {
				System.out.print(it.next());
			}
			System.out.println();
		}
	}
}
