package cn.sspku.zx.aad.ds.graph;

/**
 * undirected graph with adjacency list implementation
 * 
 * @author zhangxu
 * 
 */
public class Graph {
	private static class AdjListNode {
		private int dest;
		private AdjListNode next;

		public AdjListNode(int _dest) {
			dest = _dest;
			next = null;
		}

		@Override
		public String toString() {
			return Integer.toString(dest);
		}
	}

	private static class AdjList {
		private AdjListNode header;

		public AdjList() {
			header = null;
		}

		public void addEdge(int w) {
			AdjListNode node = new AdjListNode(w);
			node.next = header;
			header = node;
		}

		@Override
		public String toString() {
			String ret = "";
			AdjListNode p = header;
			while (null != p) {
				ret += "\t" + p;
				p = p.next;
			}

			return ret;
		}
	}

	private AdjList[] arr;

	public Graph(int v) {
		arr = new AdjList[v];
		for (int idx = 0; idx < v; ++idx)
			arr[idx] = new AdjList();
	}

	public void AddEdge(int v, int w) {
		if (v < 0 || v >= arr.length || w < 0 || w >= arr.length)
			throw new IllegalArgumentException();
		// undirected graph
		arr[v].addEdge(w);
		arr[w].addEdge(v);
	}

	@Override
	public String toString() {
		String ret = "";
		for (int idx = 0; idx < arr.length; ++idx)
			ret += "vertex " + idx + " has the following adjacency node:\n"
					+ arr[idx] + "\n";

		return ret;
	}
}
