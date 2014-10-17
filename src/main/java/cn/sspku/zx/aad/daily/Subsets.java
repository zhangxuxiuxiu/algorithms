package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If S = [1,2,3], a solution
 * is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * @author zhangxu
 * 
 */
public class Subsets {
	List<List<Integer>> ret = new ArrayList<List<Integer>>();

	public List<List<Integer>> subsets(int[] S) {
		if (null != S)
		{
			Arrays.sort(S);
			subsetsOfN(S, 0, new LinkedList<Integer>());
		}
		return ret;
	}

	private void subsetsOfN(int[] s, int counter, List<Integer> lst) {
		if (counter < s.length) {
			// 没有index为counter的数
			subsetsOfN(s, counter + 1, copy(lst));
			// 有index为counter的数
			List<Integer> added = copy(lst);
			added.add(s[counter]);
			subsetsOfN(s, counter + 1, added);
		}
		else
		{
			ret.add(lst);
		}
	}

	private List<Integer> copy(List<Integer> list) {
		List<Integer> lst = new LinkedList<Integer>();
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			lst.add(new Integer(it.next()));
		}

		return lst;
	}
}
