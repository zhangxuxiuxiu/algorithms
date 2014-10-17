package cn.sspku.zx.aad.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * @author zhangxu
 * 
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] num) {
		if (num == null || num.length < 1)
			return 0;
		int longest = 1;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < num.length; i++) {
			int x = num[i];
			if (set.contains(x))
				continue;
			set.add(x);
			int min = x, max = x;

			if (map.containsKey(x + 1)) {
				max = map.get(x + 1);
				map.remove(map.get(x + 1));
				map.remove(x + 1);
			}

			if (map.containsKey(x - 1)) {
				min = map.get(x - 1);
				map.remove(map.get(x - 1));
				map.remove(x - 1);
			}
			map.put(min, max);
			map.put(max, min);
			longest = longest > max - min + 1 ? longest : max - min + 1;
		}
		return longest;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 100, 4, 200, 1, 3, 2 };

		System.out.println(new LongestConsecutiveSequence()
				.longestConsecutive(arr));
	}
}
