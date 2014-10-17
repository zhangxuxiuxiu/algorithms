package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list
 * of its neighbors.
 * 
 * 
 * OJ's undirected graph serialization: Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node. As an example, consider the serialized graph
 * {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as
 * separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2. Second node
 * is labeled as 1. Connect node 1 to node 2. Third node is labeled as 2.
 * Connect node 2 to node 2 (itself), thus forming a self-cycle. Visually, the
 * graph looks like the following:
 * 
 * 1 / \ / \ 0 --- 2 / \ \_/
 * 
 * @author zhangxu
 * 
 */
public class GraphCloner {
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	};

	private HashMap<UndirectedGraphNode, UndirectedGraphNode> hasher = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
	private ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
	private HashSet<UndirectedGraphNode> added = new HashSet<UndirectedGraphNode>();// visited = new HashSet<UndirectedGraphNode>(),			
	int idx = 0;

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		 if(null==node) return null;
		
		UndirectedGraphNode tpr,tprCopy,next;
		nodes.add(node);
		added.add(node);
		
		while (idx < added.size()) {
			tpr = nodes.get(idx);
			tprCopy=getCopier(tpr);
			Iterator<UndirectedGraphNode> it = tpr.neighbors.iterator();
			while (it.hasNext()) {
				next=it.next();
				UndirectedGraphNode copy = getCopier(next);
				tprCopy.neighbors.add(copy);
				
				if (!added.contains(next))
				{
					nodes.add(next);
					added.add(next);
				}
			}
			++idx;
		}

		return hasher.get(node);
	}

	private UndirectedGraphNode getCopier(UndirectedGraphNode p) {
		if (!hasher.containsKey(p)) {
			UndirectedGraphNode tpr = new UndirectedGraphNode(p.label);
			hasher.put(p, tpr);
			return tpr;
		}

		return hasher.get(p);
	}
}
