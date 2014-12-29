package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, S, return all
 * possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If S = [1,2,2], a solution
 * is:
 * 
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * @author zhangxu
 * 
 */
public class SubsetsII {
	List<List<Integer>> ret = new ArrayList<List<Integer>>();

	public List<List<Integer>> subsetsWithDup(int[] S) {
		if (null != S) {
			Arrays.sort(S);
			subsetsOfN(S, 0, new LinkedList<Integer>());
		}
		return ret;
	}

	private void subsetsOfN(int[] s, int counter, List<Integer> lst) {
		if (counter < s.length) {
			int incr = 1;
			while (counter + incr < s.length && s[counter] == s[counter + incr])
				++incr;
			
			//重复的数字出现0到incr个共incr+1种情况
			subsetsOfN(s, counter + incr, copy(lst));
			for(int idx=0;idx<incr;++idx)
			{
				// 有index为counter的数
				List<Integer> added = copy(lst);
				for(int tpr=0;tpr<idx+1;++tpr)
				{
					added.add(s[counter]);
				}
				subsetsOfN(s, counter + incr, added);
			}
		} else {
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
