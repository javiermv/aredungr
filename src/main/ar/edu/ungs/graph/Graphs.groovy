package ar.edu.ungs.graph

import java.util.BitSet;
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
	

	/** Creates the set of graphs with <code>n</code> nodes and <code>e</code> edges.
	 * Los nodos de estos grafos se etiquetan con los números [0..(n-1)] 
	 */
	static Set<Graph> genSet(int n, int e) {
		Set<Graph> result = new LinkedHashSet<Graph>();
		// Dependiendo de la forma de enumerar los grafos que use el algoritmo llamado será el rango de valores
		// donde se mueva el índice 'it'. La implementación actual es mala porque usa números muy grandes y una
		// forma ingenua de enumerar los grafos. Si se quisiera una mejor implementación se podría dedicar tiempo
		// a buscar una mejor enumeración; quizás una que sólo enumere los grafos de 'e' arcos.
		final int maxEdges = n * (n-1) / 2; // FIXME: cada tanto groovy descarrila con el '/2'.
		for (it in 0..2**maxEdges) {		// 2^m es un número muy grande
			if (BigInteger.bitCount(it) == e) {
				result.add(gen(n, it))
			}
		}
		return result; 
	}
	
	// ¿Funcionará bien esto sin recibir los nodos y construyéndolos sobre la marcha?
//	static Set<Graph.Edge> intToEdgeCombination(Set<Graph.Node> nodes, BigInteger combinationIndex) { 
	static Set<Graph.Edge> intToEdgeCombination(int n, BigInteger combinationIndex) {
		// Es canchero disociar a) la enumeración, b) la construcción del grafo y c) la construcción de los ejes. De esa forma 
		// con un índice alcanzaría para construir un conjuntos de ejes, sin saber a qué grafo corresponden, y así evitar la
		// redundancia actual en las implementaciones.
		Set<Graph.Edge> result = new LinkedHashSet<Graph.Edge>();
		
		// Cada combinationIndex indica un elemento del conjunto de partes de los arcos posibles. Por eso si el grafo deseado
		// tiene 'n' nodos y un máximo de m = n * (n-1) / 2 arcos, combinationIndex está en [0..2**m]
		
//		def currentCombination = combinationIndex
		int left = 0, right = 1
//		while (currentCombination != 0) {
		// TODO: parece haber un problema de groovy acá
	    // Al ejecutar aparece: Cannot use rightShift() on this number type: java.math.BigInteger with value: 1
//		for (def it = combinationIndex; it != 0; it >> 1) {	
//		for (BigInteger it = combinationIndex; it != 0; it.rightShift(1)) {
		for (BigInteger it = combinationIndex; it != 0; it *= 0.5) {	
//			int i = BigInteger.bitCount(currentCombination); // no era esto lo que quería, sino el más bit
//			int bit = currentCombination.bitLength()
//			int bit = currentCombination.getLowestSetBit()
//			int bit = currentCombination.rightShift operand
//			int bit = currentCombination.setBit n
//			int bit = currentCombination.shiftRight n
//			int bit = currentCombination >> n
//			int bit = currentCombination.testBit n
			if (it.testBit(0))
				result.add(new Graph.Edge(left, right))
			if (right == n-1) {
				++left;
				right = left + 1;
			} else {
				++right;
			}
		}
		
		
//		BitSet edgesBitSet = new BitSet()
//		combinationIndex.
		return result;
	}

	/**
	 * Devuelve el grafo con la combinación número 'index' de arcos.
	 * La forma de numerar los grafos es un poco brutal: toma en cuenta las O(2^{n²}) combinaciones, lo que resulta un 
	 * desperdicio importante ya que incluye muchas no factibles (sin la cantidad de arcos deseada) y gran cantidad de
	 * repeticiones.
	 */
	static Graph genInstance(int nodeCount, BigInteger index) {
		final int maxEdges = nodeCount * (nodeCount-1) / 2; // FIXME: cada tanto groovy descarrila con el '/2'.
//		if (n < 0 || e < 0 || e > (n * (n-1) / 2)) // FIXME: groovy descarrila en el '/2' si no están los '()'. 
		if (nodeCount < 0 || index < 0 || index >= 2**maxEdges) 
			throw new RuntimeException("There's no combination #${index} for graphs with $nodeCount nodes.")

		if (nodeCount == 0)		// Evita rango inválido.
			return new Graph();

		// No tiene mucho sentido construir en este método los nodos ya que bien podrían ser compartidos con otros grafos
		// con igual cantidad de nodos.
		Set<Graph.Node> nodes = new LinkedHashSet<Graph.Node>(nodeCount);
		for(it in 0..nodeCount-1) {
			nodes.add(new Graph.Node(it))
		}
		return new Graph(nodes, intToEdgeCombination(nodeCount, index))
		
	}
	
	/**
	 * 'i' es el 'nro' de grafo de con 'n' nodos y 'e' ejes, en la enumeración de todos los grafos con iguales 'n' y 'e'.
	 * Esta implementación asumía una mejor forma de enumerar grafos de la que actualmente se está pensando y por lo 
	 * tanto queda en desuso hasta tanto se le dedique el tiempo suficiente para reflotarla.
	 */
/*	static Graph get(int n, int e, int i) {
		final int maxEdges = n * (n-1) / 2; // FIXME: cada tanto groovy descarrila con el '/2'.
//		if (n < 0 || e < 0 || e > (n * (n-1) / 2)) // FIXME: groovy descarrila en el '/2' si no están los '()'. 
		if (n < 0 || e < 0 || e > maxEdges) 
			throw new RuntimeException("Graphs cannot have $n nodes and $e edges.")

		if (n == 0)
			return new Graph();
		
		Set<Graph.Node> nodes = new LinkedHashSet<Graph.Node>(n);
		for(it in [1..n]) {
			nodes.add(new Graph.Node(it))
		}
		
		// Dependiendo de la implementación de abajo si es necesario esto o no.
		// Si se usa
//		if (e == 0)
//			return new Graph(nodes, Collections.emptySet());
		
		Set<Graph.Edge> edges = new LinkedHashSet<Graph.Edge>(e);
	
		// NOTA: idea muy simple. Enumerar todas las 2^m combinaciones y descartar las que no cumplan con el criterio.
		// Lento pero funciona para grafos pequeños.
		
		final BigInteger edgesCombinations = 2**maxEdges; // 2^m
		
		// Cada valor de 'it' representa un subconjunto de los 
//		edges = intToEdgeCombination(nodes, it)
		
		// Vieja nota: otro enfoque. Repartir a izquierda las 'e' líneas e ir desplazándolas como bolitas en todas las
		// ubicaciones posibles. Para esto también hay que pensar un poco; paso a algo aún más simple.
//		BitSet edgesBitSet = new BitSet(maxEdges);
//		for (it in [1..e]) {
//			edgesBitSet[it] = true;
//		}
		
		// NOTA: el enfoque de abajo se supone que permite generar más rápidamente y con menos repeticiones instacias
		// de trabajo pero requiere pensar un poco más que el enfoque simple de arriba, más lento, que genera grafos
		// isomorfos pero permite arrancar rápido con una implementación andando.
		// Vieja nota: voy a armar siempre el primer grafo, que ya tiene sus complicaciones.
		// Para arrancar asumo que 'n' es suficientemente grande, o 'e' suficientemente chico, como para que el primer
		// nodo pueda tener grado 'e'; es decir, n > e o algo así.
		// Vieja nota: ojo que no es lo mismo que el primer nodo apunte al segundo, con grado > 0, que apuntando a uno
		// de los siguientes con grado == 0.
		
//		int left = 1;
//		for (int remainingEdges = e; remainingEdges; ) {
//			++left
//			for (int right = left+1; remainingEdges && right <= n; ++right) {
//				--remainingEdges
//			}
//			
//		}
		
	}
*/	

}
