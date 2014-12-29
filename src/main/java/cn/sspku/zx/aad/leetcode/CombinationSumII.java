package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note: All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
 * … ≤ ak). The solution set must not contain duplicate combinations. For
 * example, given candidate set 10,1,2,7,6,1,5 and target 8, A solution set is:
 * [1, 7] [1, 2, 5] [2, 6] [1, 1, 6]
 * 
 * @author zhangxu
 * 
 */
public class CombinationSumII {
	void combine(List<List<Integer>> combinations, ArrayList<Integer> combine,
			int[] candidates, int target, int start) {
		if (target == 0) {
			combinations.add(copy(combine));
			return;
		}

		if (start < candidates.length) {
			int end = start + 1;
			while (end < candidates.length
					&& candidates[end] == candidates[start])
				++end;

			combine(combinations, combine, candidates, target, end);
			int idx;
			for (idx = start; idx < end && candidates[idx] <= target; ++idx) {
				combine.add(candidates[start]);
				combine(combinations, combine, candidates,
						target -= candidates[start], end);
			}
			for (int i = start; i < idx; ++i)
				combine.remove(combine.size() - 1);
		}
	}

	private List<Integer> copy(ArrayList<Integer> arrayList) {
		List<Integer> ret = new ArrayList<Integer>();
		for (int idx = 0; idx < arrayList.size(); ++idx)
			ret.add(arrayList.get(idx));
		return ret;
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> combinations = new ArrayList<List<Integer>>();
		if (null == candidates || candidates.length == 0) {
			combinations.add(null);
			return combinations;
		}

		Arrays.sort(candidates);
		ArrayList<Integer> combine = new ArrayList<Integer>();
		combine(combinations, combine, candidates, target, 0);

		return combinations;
	}

	public static void main(String[] args) {
		// Input: [1,2], 4
		// Output: [[1,1,1,1],[1,1,2]]
		// Expected: [[1,1,1,1],[1,1,2],[2,2]]
		Iterator<List<Integer>> itt = new CombinationSumII().combinationSum2(
				new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8).iterator();
		while (itt.hasNext()) {
			Iterator<Integer> it = itt.next().iterator();
			while (it.hasNext())
				System.out.print(it.next());
			System.out.println();
		}
	}
}
