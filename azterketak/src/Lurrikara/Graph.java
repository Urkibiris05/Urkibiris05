package Lurrikara;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	protected final int DEFAULT_CAPACITY = 100;
	protected int numVertices;
	protected boolean[][] adjMatrix;
	protected String[] vertices;
	
	public int index(String t) {
		int i = 0;
		for (String v: vertices) {
			if (v.equals(t)) return i;
			i++;
		}
		return -1;
	}
	
	public ArrayList<String> kaltetutakoak(int intentsitatea, String l){
		ArrayList<String> emaitza = new ArrayList<String>();
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		boolean[] aztertuak = new boolean[numVertices];
		Integer unekoa = index(l);
		aztertuGabeak.add(unekoa);
		aztertuak[unekoa]=true;
		emaitza.add(vertices[unekoa]);
		double[] intentsitateak = new double[numVertices];
		intentsitateak[unekoa] = intentsitatea;
		while (!aztertuGabeak.isEmpty()) {
			unekoa = aztertuGabeak.remove();
			if (intentsitateak[unekoa]/2>1) {
				for (int i=0; i<numVertices; i++) {
					if(adjMatrix[unekoa][i] && !aztertuak[i]) {
						aztertuak[i]=true;
						aztertuGabeak.add(i);						
						emaitza.add(vertices[i]);
						intentsitateak[i]=intentsitateak[unekoa]/2;
					}
				}
			}
		}
		return emaitza;
	}
}
