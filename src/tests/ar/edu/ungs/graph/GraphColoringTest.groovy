package ar.edu.ungs.graph;

import groovy.util.GroovyTestCase;

class GraphColoringTest extends GroovyTestCase {
	
	
	public void testFastEquitableHajnalSzemerediNullGraphColoring() {
		Graph g = new Graph()
		GraphColoring.fastEquitableHajnalSzemeredi(g)
	}
	
	public void testExactEquitableNullGraphColoring() {
		Graph g = new Graph()
		GraphColoring.exactEquitable(g)
	}
	
	public void testExactNullGraphColoring() {
		Graph g = new Graph()
		assert GraphColoring.exact(g).size() == 0
	}
	
	public void testExactTrivialGraphColoring() {
		Graph g = new Graph() + new Graph.Node("a")
		assert GraphColoring.exact(g).size() == 1
	}

	
	
}
