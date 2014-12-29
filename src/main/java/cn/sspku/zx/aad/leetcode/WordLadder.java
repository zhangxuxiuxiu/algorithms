package cn.sspku.zx.aad.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"] As
 * one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note: Return 0 if there is no such transformation sequence. All words have
 * the same length. All words contain only lowercase alphabetic characters.
 * 
 * @author zhangxu
 * 
 */
public class WordLadder {
	private String start, end;
	private String[] words;
	private boolean[] taken;
	private int[][] distances;

	// BST with distance matrix
	public int ladderLength(String start, String end, Set<String> dict) {
		ConstructWordLadder(start, end, dict);

		return shortestPath();
	}

	public void ConstructWordLadder(String start, String end, Set<String> dict) {
		// this.start = start;
		// this.end = end;
		words = dict.toArray(new String[0]);
		distances = calculateDistance(start, end, words);
		taken = new boolean[distances.length];
	}

	public int shortestPath() {
		print(distances);
		int cur, count1 = 1, count2, pathLen = 0;
		LinkedList<Integer> level = new LinkedList<Integer>();
		level.add(0);
		while (true) {
			count2 = 0;
			while (count1 > 0) {
				cur = level.removeFirst();

				if (distances[cur][taken.length - 1] == 1)
					return pathLen + 2;// return 最短路径
				for (int idx = 1; idx < taken.length - 1; ++idx)
					if (!taken[idx] && distances[cur][idx] == 1) {
						taken[idx] = true;
						level.addLast(idx);
						++count2;
					}
				--count1;
			}
			if (0 == count2)
				return 0;
			count1 = count2;
			++pathLen;
		}
	}

	private int[][] calculateDistance(String start, String end, String[] words) {
		// distances[i][j]表示第I个单词到第J个单词间的距离,start,end和words共N+2个单词
		int n = words.length + 2;
		int[][] distances = new int[n][n];
		// 每一个单词到自身的距离都是0；
		for (int idx = 0; idx < n; ++idx)
			distances[idx][idx] = 0;
		// 每一个单词到start的距离
		for (int r = 1; r < n - 1; ++r)
			distances[0][r] = distances[r][0] = distanceBetweenWords(start,
					words[r - 1]);
		distances[0][n - 1] = distances[n - 1][0] = distanceBetweenWords(start,
				end);
		// 每一个单词到end的距离
		for (int r = 1; r < n - 1; ++r)
			distances[n - 1][r] = distances[r][n - 1] = distanceBetweenWords(
					end, words[r - 1]);
		// 处理words内部单词的情况
		for (int r = 1; r < n - 1; ++r)
			for (int c = 1; c < r; ++c)
				distances[r][c] = distances[c][r] = distanceBetweenWords(
						words[c - 1], words[r - 1]);

		return distances;
	}

	// BST without building distance matrix
	public int ladderLength2(String start, String end, Set<String> dict) {
		ConstructWordLadder2(start, end, dict);

		return shortestPath2();
	}

	public void ConstructWordLadder2(String start, String end, Set<String> dict) {
		this.start = start;
		this.end = end;
		words = dict.toArray(new String[0]);
		distances = null;
		taken = new boolean[words.length];
	}

	public int shortestPath2() {
		int cur, count1 = 1, count2, pathLen = 0;
		String from;
		LinkedList<Integer> level = new LinkedList<Integer>();
		level.add(-1);
		while (true) {
			count2 = 0;
			while (count1 > 0) {
				cur = level.removeFirst();
				if (-1 == cur)
					from = start;
				else
					from = words[cur];

				if (distanceBetweenWords(from, end) == 1)
					return pathLen + 2;// return 最短路径
				for (int idx = 0; idx < words.length; ++idx)
					if (!taken[idx]
							&& distanceBetweenWords(from, words[idx]) == 1) {
						taken[idx] = true;
						level.addLast(idx);
						++count2;
					}
				--count1;
			}
			if (0 == count2)
				return 0;
			count1 = count2;
			++pathLen;
		}
	}

	/**
	 * 如果两个单词只有一个字母不同，返回1，否则返回-1.
	 * 
	 * @param one
	 * @param other
	 * @return
	 */
	int distanceBetweenWords(String one, String other) {
		int idx = 0;
		while (idx < one.length() && one.charAt(idx) == other.charAt(idx))
			++idx;
		if (idx == one.length())
			return -1;

		++idx;
		while (idx < one.length() && one.charAt(idx) == other.charAt(idx))
			++idx;

		return idx == one.length() ? 1 : -1;
	}

	static void print(int[][] board) {
		for (int r = 0; r < board.length; ++r) {
			for (int c = 0; c < board[r].length; ++c)
				System.out.printf("%5d", board[r][c]);
			System.out.println();
		}
	}

	public int ladderLength3(String start, String end, Set<String> dict) {
		int len = 0;
		char[] current;
		String tpr;
		Set<String> contains = new HashSet<String>();
		contains.add(start);
		LinkedList<String> level1 = new LinkedList<String>(), level2 = new LinkedList<String>(), t;
		level1.add(start);
		while (true) {
			while (level1.size() > 0) {
				current = level1.removeFirst().toCharArray();
				for (int idx = 0; idx < current.length; ++idx) {
					char c = current[idx];
					for (char ch = 'a'; ch <= 'z'; ++ch)
						if (ch != c) {
							current[idx] = ch;
							tpr = new String(current);
							if (tpr.equals(end))
								return len + 2;
							if (!contains.contains(tpr) && dict.contains(tpr)) {
								level2.add(tpr);
								contains.add(tpr);
							}
						}
					current[idx] = c;
				}
			}

			if (level2.size() == 0)
				return 0;
			++len;
			t = level1;
			level1 = level2;
			level2 = t;
		}
	}

	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");

		Utils.Print("" + wl.ladderLength3("hit", "cog", dict));
	}
}
