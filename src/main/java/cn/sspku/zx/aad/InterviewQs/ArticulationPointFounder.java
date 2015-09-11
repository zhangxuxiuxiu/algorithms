package cn.sspku.zx.aad.InterviewQs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.sspku.zx.aad.leetcode.Utils;

/**
 * 求一个有向连通图的割点，割点的定义是，如果除去此节点和与其相关的边， 有向图不再连通，描述算法。
 * 
 * solution:在介绍算法之前，先介绍几个基本概念
 * 
 * DFS搜索树：用DFS对图进行遍历时，按照遍历次序的不同，我们可以得到一棵DFS搜索树，如图(b)所示。
 * 树边：（在[2]中称为父子边），在搜索树中的实线所示，可理解为在DFS过程中访问未访问节点时所经过的边。
 * 回边：（在[2]中称为返祖边、后向边），在搜索树中的虚线所示，可理解为在DFS过程中遇到已访问节点时所经过的边。 基于DFS的算法
 * 
 * 该算法是R.Tarjan发明的。观察DFS搜索树，我们可以发现有两类节点可以成为割点：
 * 
 * 对根节点u，若其有两棵或两棵以上的子树，则该根结点u为割点；
 * 对非叶子节点u（非根节点），若其子树的节点均没有指向u的祖先节点的回边，说明删除u之后，根结点与u的子树的节点不再连通；则节点u为割点。
 * 对于根结点，显然很好处理；但是对于非叶子节点，怎么去判断有没有回边是一个值得深思的问题。
 * 
 * 我们用dfn[u]记录节点u在DFS过程中被遍历到的次序号，low[u]记录节点u或u的子树通过非父子边追溯到最早的祖先节点（即DFS次序号最小），
 * 那么low[u]的计算过程如下： low[u]={min{low[u], low[v]} when:(u,v)为树边 or min{low[u],
 * dfn[v]} when:(u,v)为回边且v不为u的父亲节点.
 * 
 * @author zhangxu
 * 
 */
public class ArticulationPointFounder {
	private BiGraph graph;
	private Set<Integer> points;// record all articulation points
	int counter;
	int[] dfn, low, parent;
	boolean[] isVisited;

	private void init(BiGraph g) {
		graph = g;
		points = new HashSet<Integer>();// record all articulation points
		counter = 0;
		dfn = new int[graph.V()];
		low = new int[graph.V()];
		parent = new int[graph.V()];
		Arrays.fill(parent, -1);
		isVisited = new boolean[graph.V()];
		Arrays.fill(isVisited, false);
	}

	public Iterable<Integer> findArticulationPoints(BiGraph graph) {
		init(graph);
		dfs(0);
		return points;
	}

	private void dfs(int v) {
		int children = 0;
		dfn[v] = low[v] = ++counter;
		isVisited[v] = true;

		Iterator<Integer> it = graph.adj(v).iterator();
		int w;
		while (it.hasNext()) {
			w = it.next();
			// 如果是树边
			if (!isVisited[w]) {
				// 增加子树量
				++children;
				// 记录父亲节点
				parent[w] = v;
				// 探索下一个节点
				dfs(w);
				// 跟新节点v所能到达的最小父节点
				low[v] = Math.min(low[w], low[v]);

				// case 1： 如果节点v是根节点且拥有多个子树，则节点v一定是割点
				if (parent[v] == -1 && children > 1) {
					points.add(v);
					System.out.println("root vertex with over 1 child node:"
							+ v);
				}

				// case 2： 如果节点v是叶子节点且节点v的所有子树w都不能到达v的父节点，则节点v一定是割点
				if (parent[v] != -1 && low[w] >= dfn[v]) {
					points.add(v);
					System.out.println("leaf vertex which could return:" + v
							+ "  related w:" + w);
				}
			}
			// 如果是回边（[v,w]是树边，[w,v]是回边，需避免这种情况）
			else if (w != parent[v]) {
				low[v] = Math.min(low[v], dfn[w]);
			}
		}
	}

	public static void main(String[] args) {
		BiGraph graph = new BiGraph(13);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(0, 11);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(1, 6);
		graph.addEdge(1, 7);
		graph.addEdge(1, 12);
		graph.addEdge(3, 4);
		graph.addEdge(6, 7);
		graph.addEdge(6, 8);
		graph.addEdge(6, 10);
		graph.addEdge(7, 10);
		graph.addEdge(9, 11);
		graph.addEdge(9, 12);
		graph.addEdge(11, 12);

		Utils.PrintIterableInt(new ArticulationPointFounder()
				.findArticulationPoints(graph));
	}

	public static class BiGraph {
		ArrayList<List<Integer>> graph;

		public BiGraph(int v) {
			graph = new ArrayList<List<Integer>>();
			for (int idx = 0; idx < v; ++idx)
				graph.add(new LinkedList<Integer>());
		}

		public int V() {
			return graph.size();
		}

		public Iterable<Integer> adj(int v) {
			return graph.get(v);
		}

		public void addEdge(int v, int w) {
			if (v < 0 || v >= V() || w < 0 || w >= V())
				throw new IllegalArgumentException();
			graph.get(v).add(w);
			graph.get(w).add(v);
		}
	}
}
