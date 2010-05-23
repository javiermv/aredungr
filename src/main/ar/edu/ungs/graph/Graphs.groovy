package ar.edu.ungs.graph

import java.util.Collections;
import java.util.Map;

class Graphs {

	static boolean isNull(Graph g) { return g.nodes.isEmpty() }
	
	static int maxDegree(Graph g) {
		// NOTE: nullCheck avoids exception at Collections.max() 
		if (isNull(g))
			return 0;
		Map<Graph.Node, Integer> degrees = new HashMap<Graph.Node, Integer>()
		g.nodes.each { 
			degrees[it] = 0
		}
		g.edges.each { 
			++degrees[it.left]
			++degrees[it.right]
		}
		return Collections.max(degrees.values())
	}
	
}
