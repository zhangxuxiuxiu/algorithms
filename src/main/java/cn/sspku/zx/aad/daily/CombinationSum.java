package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note: All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
 * … ≤ ak). The solution set must not contain duplicate combinations. For
 * example, given candidate set 2,3,6,7 and target 7, A solution set is: [7] [2,
 * 2, 3]
 * 
 * @author zhangxu
 * 
 */
public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
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

	void combine(List<List<Integer>> combinations, ArrayList<Integer> combine,
			int[] candidates, int target, int start) {
		if (target == 0) {
			combinations.add(copy(combine));
			return;
		}

		int idx = start;
		while (idx < candidates.length && candidates[idx] <= target) {
			combine.add(candidates[idx]);
			combine(combinations, combine, candidates,
					target - candidates[idx], idx);
			combine.remove(combine.size() - 1);
			++idx;
		}
	}

	private List<Integer> copy(ArrayList<Integer> arrayList) {
		List<Integer> ret = new ArrayList<Integer>();
		for (int idx = 0; idx < arrayList.size(); ++idx)
			ret.add(arrayList.get(idx));
		return ret;
	}

	public static void main(String[] args) {
		// Input: [1,2], 4
		// Output: [[1,1,1,1],[1,1,2]]
		// Expected: [[1,1,1,1],[1,1,2],[2,2]]
		Iterator<List<Integer>> itt = new CombinationSum().combinationSum(
				new int[] { 1, 2 }, 4).iterator();
		while (itt.hasNext()) {
			Iterator<Integer> it = itt.next().iterator();
			while (it.hasNext())
				System.out.print(it.next());
			System.out.println();
		}
	}
}
