package Kaleak;

import java.util.LinkedList;

public class GraphAL<T>{
	protected final int DEFAULT_CAPACITY = 100;
	protected int numVertices;
	protected LinkedList<Integer>[] adjList;
	protected T[] vertices;
	
	public int index(T s) {
		int i = 0;
		for (T v : vertices) {
			if (v.equals(s)) return i;
			i++;
		}
		return -1;
	}
}
