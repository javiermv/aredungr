/**
 * 
 */
package ar.edu.ungs.graph;

import groovy.util.GroovyTestCase;

/**
 * @author javiermv
 */
class GraphTest extends GroovyTestCase {

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	public void testEmptyGraphToString() {
		assert new Graph().toString() == "[]"
	}

//	public void testOneNodeNoEdgeGraphToString() {
	public void testTrivialGraphToString() {
		assert (new Graph() + new Graph.Node("a")).toString() == "[a]"
		assert (new Graph() + new Graph.Node("b")).toString() == "[b]"
	}

	public void testSomeNodesNoEdgeGraphToString() {
		assert (new Graph() + new Graph.Node("a") + new Graph.Node("b")).toString() == "[a, b]"
	}

	public void testOneNodeOneEdgeToString() {
		assert (new Graph() + new Graph.Node("a") + new Graph.Edge("a", "a")).toString() == "[[a]; [(a, a)]]"
	}
	
//	/**
//	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
//	 */
//	public void testEquals(){
//	fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link java.lang.Object#hashCode()}.
//	 */
//	public void testHashCode(){
//	fail("Not yet implemented"); // TODO
//	}

}
