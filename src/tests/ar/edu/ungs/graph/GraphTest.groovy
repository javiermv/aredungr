/**
 * 
 */
package ar.edu.ungs.graph;

import groovy.util.GroovyTestCase;

/**
 * @author javiermv
 */
class GraphTest extends GroovyTestCase {

	// TODO: see http://groovy.codehaus.org/Using+GSpec+with+Groovy
	
	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	void testEmptyGraphToString() {
		assert new Graph().toString() == "[]"
	}

//	void testOneNodeNoEdgeGraphToString() {
	void testTrivialGraphToString() {
		assert (new Graph() + new Graph.Node("a")).toString() == "[a]"
		assert (new Graph() + new Graph.Node("b")).toString() == "[b]"
	}

	void testSomeNodesNoEdgeGraphToString() {
		assert (new Graph() + new Graph.Node("a") + new Graph.Node("b")).toString() == "[a, b]"
	}

	void testOneNodeOneEdgeToString() {
		assert (new Graph() + new Graph.Node("a") + new Graph.Edge("a", "a")).toString() == "[[a]; [(a, a)]]"
	}
	
//	/**
//	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
//	 */
//	void testEquals() {
//	fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link java.lang.Object#hashCode()}.
//	 */
//	void testHashCode() {
//	fail("Not yet implemented"); // TODO
//	}

}
