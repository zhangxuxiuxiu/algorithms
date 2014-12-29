package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [1,1,2],
 * [1,2,1], and [2,1,1].
 * 
 * @author zhangxu
 * 
 */
public class PermutationsII {
	public List<List<Integer>> permuteUnique(int[] num) {
		List<List<Integer>> ps = new ArrayList<List<Integer>>();
		if (null == num || num.length == 0) {
			ps.add(null);
			return ps;
		}

		Arrays.sort(num);
		ps.add(onePermutation(num));
		while (nextPermutation(num))
			ps.add(onePermutation(num));

		return ps;
	}

	private List<Integer> onePermutation(int[] num) {
		List<Integer> lst = new ArrayList<Integer>();
		for (int i = 0; i < num.length; ++i)
			lst.add(num[i]);
		return lst;
	}

	public boolean nextPermutation(int[] num) {
		if (null == num || num.length < 2)
			return false;

		int idx = num.length - 2;
		// 从右到左寻找第一对左边小于右边的数字对
		while (idx > -1 && num[idx] >= num[idx + 1])
			--idx;
		// 如果找到了
		if (idx != -1) {
			int i = num.length - 1;
			while (num[i] <= num[idx])
				--i;
			swap(num, idx, i);
			Arrays.sort(num, idx + 1, num.length);
			return true;
		}
		return false;
	}

	// private void permute(List<List<Integer>> ps, int[] num, int k) {
	// if (k == num.length) {
	// List<Integer> lst = new ArrayList<Integer>();
	// for (int idx = 0; idx < num.length; ++idx)
	// lst.add(num[idx]);
	//
	// ps.add(lst);
	// return;
	// }
	// // 找到与当前数字相等区间的上界和下界
	// int start = k, end = k;
	// while (start > 0 && num[start - 1] == num[k])
	// --start;
	// while (end < num.length - 1 && num[end + 1] == num[k])
	// ++end;
	// System.out.println("start=" + start + "  end=" + end);
	// if (end == num.length - 1)
	// permute(ps, num, num.length);
	// else
	// // 对于相等的数字，从右到左依次与不同的数字区域的首数字交换
	// for (int idx = end; idx >= start; --idx)
	// for (int i = idx; i < num.length;) {
	// // 与相等数字区域首个数字进行交换
	// swap(num, idx, i);
	// System.out.println("swap between " + idx + " and " + i);
	// // 寻找下个相等数字区域的首数字
	// int len = 1;
	// while (i + len < num.length && num[i + len] == num[i])
	// ++len;
	// i += len;
	// System.out.println("permute next: " + i);
	// permute(ps, num, i);
	// swap(num, idx, i - len);
	// }
	// }

	void swap(int[] num, int i, int j) {
		if (i != j) {
			int tpr = num[i];
			num[i] = num[j];
			num[j] = tpr;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> permutes = new PermutationsII()
				.permuteUnique(new int[] { -1, -1, 3, -1 });

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
