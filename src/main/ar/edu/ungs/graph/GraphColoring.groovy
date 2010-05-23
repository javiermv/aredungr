package ar.edu.ungs.graph

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import static ar.edu.ungs.graph.Graphs.*;

class GraphColoring {

	static class Color extends BigInteger { 
		Color(int color) { super(color) } // TODO: buscar constructor apropiado; sino cambiar BigInteger por su superclase (¿Number?)
	}
	
	static void fastEquitableHajnalSzemeredi(Graph g) {
//		println "hola"
	}
	
	static void exactEquitable(Graph g) {
//		println "exactEquitable"
	}
	
	static Map<Graph.Node, Color> exact(Graph g) {
		for (colors in 0..maxDegree(g)) {
			def kColoring = kColoring(colors, g) 
			if (kColoring != null) // accepts empty kColoring for nullGraph 
				return kColoring
		}
		return null
	}
	
	static Map<Graph.Node, Color> kColoring(int k, Graph g) {
		Map<Graph.Node, Color> result = new LinkedHashMap<Graph.Node, Color>()
		
		println "k = " + k
		Set<Graph.Node> pending = new LinkedHashSet<Graph.Node>(g.nodes) 
		Map<Graph.Node, Color> partial = new LinkedHashMap<Graph.Node, Color>()
		
		if (pending.isEmpty())
			return partial
		
		pending.each {
			println it
			for (Color color = new Color(0); color < k; ++color) {
				
			}
		}
		
		
		if (!isNull(g))
			result[new Graph.Node("X")] = k
//		println "kcoloring: " + result
		return result
	}
	
	
	
	
	
}
