package OndarearenBanaketa;

import java.util.LinkedList;

public class GraphAL<T> {
	protected final int DEFAULT_CAPACITY=100;
	protected int numVertices;
	protected LinkedList<Integer>[] adjList;
	protected T[] vertices;
	
	public int index(T t) {
		int i=0;
		for (T vertex : vertices) {
			if (vertex.equals(t)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
