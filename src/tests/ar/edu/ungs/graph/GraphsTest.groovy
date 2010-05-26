package ar.edu.ungs.graph;

import groovy.util.GroovyTestCase;
import static ar.edu.ungs.graph.Graphs.*;

class GraphsTest extends GroovyTestCase {

	void testNullGraphDegree() {
		assert maxDegree(new Graph()) == 0 
	}

	void testTrivialGraphDegree() {
		assert maxDegree(new Graph() + new Graph.Node("a")) == 0 
	}
	
	void testTrivialLoopGraphDegree() {
		assert maxDegree(new Graph() + new Graph.Node("a") + new Graph.Edge("a", "a")) == 2 
	}

	void testNegativeNodeCountGen() {
		def msg = "There's no combination #%d for graphs with %d nodes."
		assert shouldFail { Graphs.genInstance(-1, 0) } == String.format(msg, 0, -1)
		assert shouldFail { Graphs.genInstance(-1, 1) } == String.format(msg, 1, -1)
	}
	
	void testNegativeIndexGen() {
		def msg = "There's no combination #%d for graphs with %d nodes."
		assert shouldFail { Graphs.genInstance(0, -1) } == String.format(msg, -1, 0)
		assert shouldFail { Graphs.genInstance(1, -1) } == String.format(msg, -1, 1)
	}
	
	void testExcesiveIndexGen() {
		def msg = "There's no combination #%d for graphs with %d nodes."
		assert shouldFail { Graphs.genInstance(0, 1) } == String.format(msg, 1, 0)
		assert shouldFail { Graphs.genInstance(1, 1) } == String.format(msg, 1, 1)
		assert shouldFail { Graphs.genInstance(2, 2) } == String.format(msg, 2, 2)
	}
	
	// TODO: see http://groovy.codehaus.org/Test+Combinations

	void testNullGraphGen() {
		assert Graphs.genInstance(0, 0) == "[]"
	}
	
	void testTrivialGraphGen() {
		assert Graphs.genInstance(1, 0) == "[0]"
	}

	void testGraphN2I0Gen() {
		assert Graphs.genInstance(2, 0) == "[0, 1]"
	}
	
	void testGraphN2I1Gen() {
		assert Graphs.genInstance(2, 1) == "[[0, 1]; [(0, 1)]]"
	}
	
	void testGraphN3I0Gen() {
		assert Graphs.genInstance(3, 0) == "[0, 1, 2]"
	}
	
	void testGraphN3I1Gen() {
		assert Graphs.genInstance(3, 1) == "[[0, 1, 2]; [(0, 1)]]"
	}

	void testGraphN3I2Gen() {
		assert Graphs.genInstance(3, 2) == "[[0, 1, 2]; [(0, 2)]]"
	}

	void testGraphN3I3Gen() {
		assert Graphs.genInstance(3, 3) == "[[0, 1, 2]; [(0, 1), (0, 2)]]"
	}

	void testGraphN3I4Gen() {
		assert Graphs.genInstance(3, 4) == "[[0, 1, 2]; [(1, 2)]]"
	}

	void testGraphN3I5Gen() {
		assert Graphs.genInstance(3, 5) == "[[0, 1, 2]; [(0, 1), (1, 2)]]"
	}

	void testGraphN3I6Gen() {
		assert Graphs.genInstance(3, 6) == "[[0, 1, 2]; [(0, 2), (1, 2)]]"
	}

	void testGraphN3I7Gen() {
		assert Graphs.genInstance(3, 7) == "[[0, 1, 2]; [(0, 1), (0, 2), (1, 2)]]"
	}
	
	void testGraphGen() {
		println Graphs.genInstance(4, 63)
	}

}
