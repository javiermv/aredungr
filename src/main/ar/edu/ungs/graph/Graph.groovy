package ar.edu.ungs.graph

class Graph {

	Set<Node> nodes = new LinkedHashSet<Node>();
	Set<Edge> edges = new LinkedHashSet<Edge>();
	
	Graph plus(Node node) {
		return new Graph(nodes + node, edges)
//		nodes += node
//		return this
	}
	
	Graph plus(Edge edge) {
		return new Graph(nodes, edges + edge)
//		edges += edge
//		return this
	}
	
	Graph() { }
	
	Graph(Set<Node> nodes, Set<Edge> edges) {
		this.nodes = nodes
		this.edges = edges
	}
	
	// NOTE: hubiera sido interesante tener una inner (non-static nested) class.
	// NOTE: lo mismo vale para Edge.
	// @see http://java.sun.com/docs/books/tutorial/java/javaOO/nested.html
	// @see http://blogs.sun.com/darcy/entry/nested_inner_member_and_top
	// class Node {
	static class Node {
		String name;
		Node(String name) { this.name = name }
		String toString() { name }
		boolean equals(Object that) {
			// probar is() o isCase() en vez de == Node
			that.class == Node && this.toString() == that.toString()
//			that.isCase(Node) && this.toString() == that.toString()
		}
		int hashCode() { name.hashCode() }
	}
	
	// NOTE: valen los mismos comentarios que en Node.
	static class Edge { 
		Node left, right
		Edge(Node left, Node right) { this.left = left; this.right = right; }
		Edge(String left, String right) { this(new Node(left), new Node(right)) }
		String toString() { return "($left, $right)" }
	}
	
	Set<Node> N(Node n) {
		def result = edges.findAll { it.left == n || it.right == n }.collect{ it.left == n? it.right: it.left} // FIXME: feo
		return result
	}
	
	String toString() {
		String result = edges.isEmpty()? "$nodes": "[$nodes; $edges]"
		return result
	}

}
