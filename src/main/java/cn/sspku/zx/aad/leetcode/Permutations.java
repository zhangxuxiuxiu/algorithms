package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
 * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * @author zhangxu
 * 
 */
public class Permutations {
	public List<List<Integer>> permute(int[] num) {
		List<List<Integer>> ps = new ArrayList<List<Integer>>();
		if (null == num || num.length == 0) {
			ps.add(null);
			return ps;
		}

		permute(ps, num, 0);

		return ps;
	}

	private void permute(List<List<Integer>> ps, int[] num, int k) {
		if (k == num.length) {
			List<Integer> lst = new ArrayList<Integer>();
			for (int idx = 0; idx < num.length; ++idx)
				lst.add(num[idx]);

			ps.add(lst);
			return;
		}

		for (int i = k; i < num.length; ++i) {
			swap(num, i, k);
			permute(ps, num, k + 1);
			swap(num, k, i);
		}
	}

	void swap(int[] num, int i, int j) {
		if (i != j) {
			int tpr = num[i];
			num[i] = num[j];
			num[j] = tpr;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> permutes = new Permutations().permute(new int[] {
				1, 2, 3 });

		Iterator<List<Integer>> listIt = permutes.iterator();
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
