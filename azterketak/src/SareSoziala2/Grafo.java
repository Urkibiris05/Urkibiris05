package SareSoziala2;

import java.util.LinkedList;
import java.util.Queue;

public class Grafo<T> {
	final int DEFAULT_CAPACITY = 100;
	int num_vertices;
	T[] vertices;
	boolean [][] adjMatrix;
	LinkedList<Integer>[] adjList;
	
	public void zerrenda2Matrize() {
		adjMatrix = new boolean[num_vertices][num_vertices];
		for (int i=0; i<num_vertices; i++) {
			for(Integer ondokoak : adjList[i]) {
				adjMatrix[i][ondokoak]=true;
			}
		}
	}
	
	public void matrize2Zerrenda() {
		adjList = (LinkedList<Integer>[]) new LinkedList[num_vertices];
		for (int i=0; i<num_vertices; i++) {
		adjList[i]= new LinkedList<Integer>();
			for (int j=0; j<num_vertices; j++) {
				if (adjMatrix[i][j]) adjList[i].add(j);
			}
		}
	}
}

