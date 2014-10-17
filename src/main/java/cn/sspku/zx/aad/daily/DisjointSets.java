package cn.sspku.zx.aad.daily;

import java.util.ArrayList;

/**
 * 给定一个字符串的集合，格式如：{aaa,bbb,ccc},{bbb,ddd},{eee,fff},{ggg},{ddd,hhh}。
 * 要求将其中交集不为空的集合合并，要求合并完成的集合之间无交集，例如上例应输出{aaa,bbb,ccc,ddd,hhh}, {eee,fff},{ggg}。
 * 
 * (1) 请描述你解决这个问题的思路；
 * 
 * (2) 给出主要的处理流程，算法，以及算法的复杂度；
 * 
 * (3) 请描述可能的改进。
 * 
 * 方案：采用并查集。首先所有的字符串都在单独的并查集中。然后依扫描每个集合，顺序合并将两个相邻元素合并。例如，对于，
 * 首先查看aaa和bbb是否在同一个并查集中，如果不在，那么把它们所在的并查集合并，然后再看bbb和ccc是否在同一个并查集中，
 * 如果不在，那么也把它们所在的并查集合并。接下来再扫描其他的集合，当所有的集合都扫描完了，并查集代表的集合便是所求。
 * 复杂度应该是O(NlgN)的。改进的话，首先可以记录每个节点的根结点，改进查询。 合并的时候，可以把大的和小的进行合，这样也减少复杂度。
 */
public class DisjointSets {
	private static final int TOTALELEMENTS = 8;
	private int groups = TOTALELEMENTS;
	private int[] relation = new int[TOTALELEMENTS];// 记录每个人所在组别

	// group = new int[TOTALELEMENTS];// 每个组有多少人

	private void initialize() {
		for (int idx = 0; idx < TOTALELEMENTS; ++idx) {
			relation[idx] = idx;
			// group[idx] = 1;
		}
	}

	public int rootId(int x) {
		// 以下方法不仅查询到根节点，还缩短了其他节点的树深度
		if (relation[x] == x)
			return x;
		return relation[x] = rootId(relation[x]);
	}

	public void union(int x, int y) {
		if (x != y) {
			int _x = relation[x], _y = relation[y];
			if (_x != _y) {
				relation[_x] = _y;
				--groups;
			}
		}
	}

	public void processInput(ArrayList<ArrayList<Integer>> in) {
		ArrayList<Integer> tpr;
		for (int idx1 = 0; idx1 < in.size(); ++idx1) {
			tpr = in.get(idx1);
			if (tpr.size() > 1) {
				for (int idx2 = 0; idx2 < tpr.size() - 1; ++idx2) {
					union(tpr.get(idx2), tpr.get(idx2 + 1));
				}
			}
		}
	}

	public void printResult() {
		System.out.printf("There all %d disjoint sets in total!\n", groups);

		for (int idx = 0; idx < TOTALELEMENTS; ++idx) {
			System.out.printf("%5d", relation[idx]);
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> c1 = new ArrayList<Integer>();
		c1.add(0);
		c1.add(1);
		c1.add(2);
		ArrayList<Integer> c2 = new ArrayList<Integer>();
		c2.add(1);
		c2.add(3);
		ArrayList<Integer> c3 = new ArrayList<Integer>();
		c3.add(4);
		c3.add(5);
		ArrayList<Integer> c4 = new ArrayList<Integer>();
		c4.add(6);
		ArrayList<Integer> c5 = new ArrayList<Integer>();
		c5.add(3);
		c5.add(7);
		ArrayList<ArrayList<Integer>> in = new ArrayList<ArrayList<Integer>>();
		in.add(c5);
		in.add(c4);
		in.add(c3);
		in.add(c2);
		in.add(c1);

		DisjointSets ds = new DisjointSets();
		ds.initialize();
		ds.processInput(in);
		ds.printResult();
	}

}
