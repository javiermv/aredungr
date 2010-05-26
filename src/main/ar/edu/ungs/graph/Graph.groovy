package ar.edu.ungs.graph

class Graph {
// TODO: una implementación canchera de grafos que vi x ahí era paramétrica en los nodos. Se usaba como Graph<E> para
// indicar el tipo deseado de los elementos. Me pareció una buena idea y puede estar piola explorarla un día. 

	// NOTE: hubiera sido interesante tener una inner (non-static nested) class.
	// NOTE: lo mismo vale para Edge.
	// @see http://java.sun.com/docs/books/tutorial/java/javaOO/nested.html
	// @see http://blogs.sun.com/darcy/entry/nested_inner_member_and_top
	// class Node {
	static class Node {
		String name;
		Node(int name) { this.name = name.toString() }
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
	static class Edge implements Comparable<Edge> { 
		
		Node left, right
		
		Edge(Node left, Node right) { 
			this.left = left; this.right = right; 
		}
		
		Edge(String left, String right) { 
			this(new Node(left), new Node(right)) 
		}
		
		Edge(int left, int right) { 
			this(new Node(left), new Node(right)) 
		}
		
		@Override
		String toString() { 
			return "($left, $right)" 
		}
		
		@Override
		public int compareTo(Edge that) { 
			this.toString().compareTo(that.toString()) 
		}
		
	}
	
	// TreeSet logra que toString no requiera ordenar elementos y facilita el debugging.
	// TreeSet obliga a usar Comparable o Comparator sobre Node, que si después se quiere parametrizar resulta poco feliz.
	// LinkedHashSet agrega 'extrañeza' a expensas de intentar simplificar el debugging.
	// HashSet debería ser eficiente y sort() en toString() se encarga de facilitar, xahora, el debugging.
	Set<Node> nodes = new HashSet<Node>();
	Set<Edge> edges = new HashSet<Edge>();
	
	Graph plus(Node node) {
		return new Graph(nodes + node, edges)
	}
	
	Graph plus(Edge edge) {
		return new Graph(nodes, edges + edge)
	}
	
	Graph() { }
	
	Graph(Set<Node> nodes, Set<Edge> edges) {
		this.nodes = nodes
		this.edges = edges
	}

	/**
	 * Devuelve el conjunto de vecinos de un nodo.
	 */
	Set<Node> N(Node n) {
		def result = edges.findAll { it.left == n || it.right == n }.collect{ it.left == n? it.right: it.left} // FIXME: feo
		return result
	}
	
	boolean equals(Object that) {
		if (that.class == String)
			return toString() == that 
		else 
			return that.class == Graph && this.nodes == that.nodes && this.edges == that.edges
	}
	
	String toString() {
		// sort() permite comparar grafos mediante su representación como String.
		String result = edges.isEmpty()? "${nodes.sort()}": "[${nodes.sort()}; ${edges.sort()}]"
		return result
	}

}
