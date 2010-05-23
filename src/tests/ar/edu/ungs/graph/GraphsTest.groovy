package ar.edu.ungs.graph;

import groovy.util.GroovyTestCase;
import static ar.edu.ungs.graph.Graphs.*;

class GraphsTest extends GroovyTestCase {

	public final void testNullGraphDegree() {
		assert maxDegree(new Graph()) == 0 
	}

	public final void testTrivialGraphDegree() {
		assert maxDegree(new Graph() + new Graph.Node("a")) == 0 
	}
	
	public final void testTrivialLoopGraphDegree() {
		assert maxDegree(new Graph() + new Graph.Node("a") + new Graph.Edge("a", "a")) == 2 
	}

}
