package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
 * Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
 * Note: All words have the same length. All words contain only lowercase
 * alphabetic characters.
 * 
 * @author zhangxu
 * 
 */
public class WordLadderII {
	int minLen = Integer.MAX_VALUE;

	// TLE
	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		String[] words = dict.toArray(new String[0]);
		// 表明当前单词在不在现在的梯子上
		boolean[] onTheLadder = new boolean[words.length];
		Arrays.fill(onTheLadder, false);

		List<List<String>> ladders = new ArrayList<List<String>>();
		LinkedList<String> currentLadder = new LinkedList<String>();
		currentLadder.add(start);
		bridgeWords(start, end, words, currentLadder, onTheLadder, ladders);

		List<List<String>> shortestLadders = new ArrayList<List<String>>();
		List<String> current = null;
		Iterator<List<String>> it = ladders.iterator();
		while (it.hasNext()) {
			current = it.next();
			if (current.size() == minLen + 1)
				shortestLadders.add(current);
		}
		return shortestLadders;
	}

	/**
	 * 通过不断深度优先查找来寻找start 与 end之间的过渡单词
	 * 
	 * @param start
	 *            开始的单词
	 * @param end
	 *            最后的单词
	 * @param words
	 *            可供过渡的单词列表
	 * @param currentLadder
	 *            保存当前的梯子上的所有单词
	 * @param onTheLadder
	 *            记录当前单词是否在当前梯子上
	 * @param ladders
	 *            保存所有可能的单词梯子
	 */
	private void bridgeWords(String start, String end, String[] words,
			LinkedList<String> currentLadder, boolean[] onTheLadder,
			List<List<String>> ladders) {
		if (distanceBetweenWords(start, end) == 1) {
			currentLadder.add(end);
			ladders.add(copy(currentLadder));
			currentLadder.removeLast();
			minLen = currentLadder.size();
		} else if (currentLadder.size() < minLen)
			for (int idx = 0; idx < words.length; ++idx) {
				if (!onTheLadder[idx]
						&& distanceBetweenWords(start, words[idx]) == 1) {
					onTheLadder[idx] = true;
					currentLadder.add(words[idx]);
					bridgeWords(words[idx], end, words, currentLadder,
							onTheLadder, ladders);
					currentLadder.removeLast();
					onTheLadder[idx] = false;
				}
			}
	}

	private List<String> copy(LinkedList<String> currentLadder) {
		List<String> ret = new ArrayList<String>();
		Iterator<String> it = currentLadder.iterator();
		while (it.hasNext())
			ret.add(it.next());
		return ret;
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

	// TLE
	public List<List<String>> findLadders2(String start, String end,
			Set<String> dict) {
		List<List<String>> ladders = new ArrayList<List<String>>();

		String[] words = dict.toArray(new String[0]);
		int[][] distances = caculateDistance(start, end, words);
		// print(distances);
		// 表明当前单词在不在现在的梯子上
		boolean[] onTheLadder = new boolean[words.length + 1];
		Arrays.fill(onTheLadder, false);
		LinkedList<String> currentLadder = new LinkedList<String>();
		currentLadder.add(start);

		bridgeWords2(words, 0, end, distances, onTheLadder, currentLadder,
				ladders);

		return ladders;
	}

	private void bridgeWords2(String[] words, int wordIdx, String end,
			int[][] distances, boolean[] onTheLadder,
			LinkedList<String> currentLadder, List<List<String>> ladders) {
		if (currentLadder.size() <= minLen)
			for (int idx = distances.length - 1; idx > 0; --idx) {
				if (!onTheLadder[idx - 1] && distances[wordIdx][idx] == 1) {
					// System.out.println(idx);
					if (idx == distances.length - 1) {
						currentLadder.add(end);
						ladders.add(copy(currentLadder));
						currentLadder.removeLast();
						minLen = currentLadder.size();
					} else {
						onTheLadder[idx - 1] = true;
						currentLadder.add(words[idx - 1]);
						bridgeWords2(words, idx, end, distances, onTheLadder,
								currentLadder, ladders);
						currentLadder.removeLast();
						onTheLadder[idx - 1] = false;
					}
				}
			}
	}

	public List<List<String>> findLadders3(String start, String end,
			Set<String> dict) {
		List<List<String>> ladders = new ArrayList<List<String>>();

		String[] words = dict.toArray(new String[0]);
		int[][] distances = caculateDistance(start, end, words);
		// 记录当前单词是否已经在计算好的节点当中
		boolean[] taken = new boolean[words.length];
		Arrays.fill(taken, false);
		// 记录最短路径上的节点下标
		LinkedList<Integer> pathIndexes = new LinkedList<Integer>();
		// 记录当前单词到start的距离
		int[] sDist = new int[words.length];
		Arrays.fill(sDist, Integer.MAX_VALUE);
		int index = -1, shortestIndex, minDist;
		while (index < distances.length - 1) {
			minDist = Integer.MAX_VALUE;
			shortestIndex = -1;
			for (int next = 0; next < words.length; ++next)
				// 寻找没有加入的节点中距离最小的点
				if (!taken[next] && sDist[next] < minDist)
					shortestIndex = next;
			// 找到距离最小的节点后，更新与其相邻的节点到start的距离
			if (-1 != shortestIndex) {
				taken[shortestIndex] = true;
				pathIndexes.add(shortestIndex);
				minDist = sDist[shortestIndex];
				if (distances[shortestIndex + 1][distances.length - 1] == 1)
					ladders.add(shortestPath(start, end, words, pathIndexes));
				else
					for (int idx = 0; idx < words.length; ++idx) {
						if (!taken[idx]
								&& distances[shortestIndex + 1][idx + 1] == 1)
							sDist[idx] = Math.min(sDist[shortestIndex] + 1,
									sDist[idx]);
					}
				index = shortestIndex;
			}
		}

		return ladders;
	}

	/**
	 * 通过地杰斯特拉算法来寻找一个图中所有的最短路径
	 * 
	 * @param start
	 *            源节点下标
	 * @param end
	 *            尾节点下标
	 * @param distances
	 *            点与点之间的连通性，1表示连通，其余表示不连通
	 * @return 所有最短路径上的下标集合
	 */
	public static List<List<Integer>> Dijkstra(int start, int end,
			int[][] distances) {
		List<List<Integer>> shortestPathes = new LinkedList<List<Integer>>();
		// 记录当前节点是否已经在计算好的节点当中
		boolean[] taken = new boolean[distances.length];
		Arrays.fill(taken, false);
		taken[0] = true;
		// 记录最短路径上的节点下标
		LinkedList<Integer> pathIndexes = new LinkedList<Integer>();
		pathIndexes.add(start);

		int cur, count1 = 1, count2;
		LinkedList<Integer> level = new LinkedList<Integer>();
		level.add(start);
		while (true) {
			count2 = 0;
			while (count1 > 0) {
				cur = level.removeFirst();

				if (distances[cur][taken.length - 1] == 1)
					return shortestPathes;// return 最短路径
				for (int idx = 1; idx < taken.length - 1; ++idx)
					if (!taken[idx] && distances[cur][idx] == 1) {
						level.add(idx);
						++count2;
					}
				--count1;
			}
			count1 = count2;
		}
	}

	private List<String> shortestPath(String start, String end, String[] words,
			LinkedList<Integer> path) {
		List<String> pathes = new LinkedList<String>();
		pathes.add(start);
		Iterator<Integer> it = path.iterator();
		while (it.hasNext())
			pathes.add(words[it.next()]);
		pathes.add(end);
		return pathes;
	}

	private int[][] caculateDistance(String start, String end, String[] words) {
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

	static void print(int[][] board) {
		for (int r = 0; r < board.length; ++r) {
			for (int c = 0; c < board[r].length; ++c)
				System.out.printf("%5d", board[r][c]);
			System.out.println();
		}
	}

	private static class Vertex {
		String word;
		List<Vertex> neigbours;

		public Vertex(String w) {
			word = w;
			neigbours = new LinkedList<Vertex>();
		}

		public void AddNeigbour(Vertex v) {
			neigbours.add(v);
		}
	}

	public List<List<String>> findLadders4(String start, String end,
			Set<String> dict) {
		List<List<String>> shortestPathes = new LinkedList<List<String>>();

		char[] current;// 当前处理的字符串的字符数组形式
		String tpr, currentWord;// 当前字符串的邻近字符串
		boolean isShortestPathFound = false;// 是否已经找到第一条最短路径
		Map<String, Vertex> contains = new HashMap<String, Vertex>();// 在整个遍历过程当中当前遇到的所有字符串及其对应的Vertex节点
		contains.put(start, new Vertex(start));// 把start先加进去
		LinkedList<Map<String, Vertex>> layers = new LinkedList<Map<String, Vertex>>();// 记录每一个长度层面上存在的字符串
		Map<String, Vertex> layer1 = new HashMap<String, Vertex>();
		layer1.put(start, contains.get(start));
		layers.add(layer1);// 第一个层级上的单词只有start
		// 构建一个只包含从start到end最短路径的无向图
		while (!isShortestPathFound) {
			Map<String, Vertex> layerX = new HashMap<String, Vertex>();// 记录当前层上的单词
			// 通过对前面一个层次上每个单词的处理来寻找当前层次上的单词
			Iterator<String> it = layers.getLast().keySet().iterator();
			while (it.hasNext()) {
				// 获取当前要处理的单词
				currentWord = it.next();
				current = currentWord.toCharArray();
				if (!isShortestPathFound)
					// 找出所有的邻接单词
					for (int idx = 0; idx < current.length; ++idx) {
						char c = current[idx];
						for (char ch = 'a'; ch <= 'z'; ++ch)
							if (ch != c) {
								current[idx] = ch;
								tpr = new String(current);// 构建的当前单词的邻近单词
								// 当前单词跟end邻接，则可以退出当前单词其他邻接单词的搜索
								if (tpr.equals(end)) {
									isShortestPathFound = true;
									idx = current.length - 1;
									// 清空当前层次上的单词，因为作为最后一层，只能有一个单词，那就是end
									layerX.clear();
									layerX.put(end, new Vertex(end));
									contains.get(currentWord).AddNeigbour(
											layerX.get(end));
									break;
								}
								System.out.println(tpr);
								// 如果当前单词存在于词典当中，且前面的所有层次上的单词都不包含这个单词，
								if (dict.contains(tpr)
										&& !contains.containsKey(tpr)) {
									// 那么便需要将这个单词加到当前层次上的单词级当中
									if (!layerX.containsKey(tpr))
										layerX.put(tpr, new Vertex(tpr));
									// 并且记录当前单词到新邻近单词间的关系
									contains.get(currentWord).AddNeigbour(
											layerX.get(tpr));
								}
							}
						current[idx] = c;
					}
				// 如果已经找到了最短路径，那么只需要判断当前单词是否与end为邻接单词,如果是，记录他们之间的关系，否则不管
				else if (distanceBetweenWords(end, currentWord) == 1)
					contains.get(currentWord).AddNeigbour(layerX.get(end));
			}
			// 如果没有找到一个邻接的单词，则退出循环
			if (layerX.size() == 0)
				break;
			// 把当前层创建的所有新的节点加到节点池contains当中
			contains.putAll(layerX);
			// 把当前层次上的单词加到层当中
			layers.addLast(layerX);
		}

		print(layers);
		// 构建完整个包含所有最短路径的无向图之后，通过递归方法搜索所有最短路径
		findShortestPath(contains.get(start), end, new LinkedList<String>(),
				shortestPathes);

		return shortestPathes;
	}

	void print(LinkedList<Map<String, Vertex>> layers) {
		Map<String, Vertex> map;
		while (layers.size() > 0) {
			map = layers.removeFirst();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext())
				System.out.print("   " + it.next());
			System.out.println();
		}
	}

	// 找到所有的最短路径
	void findShortestPath(Vertex node, String end,
			LinkedList<String> currentLadder, List<List<String>> pathes) {
		currentLadder.add(node.word);
		if (end.equals(node.word))
			pathes.add(copy(currentLadder));
		else {
			Iterator<Vertex> it = node.neigbours.iterator();// 处理的所有邻接节点
			while (it.hasNext())
				findShortestPath(it.next(), end, currentLadder, pathes);
		}
		currentLadder.removeLast();// 回溯
	}

	public static void main(String[] args) {
		WordLadderII wl = new WordLadderII();
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		Utils.PrintListString(wl.findLadders4("hit", "cog", dict));
	}
}
