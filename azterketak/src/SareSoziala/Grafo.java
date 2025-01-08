package SareSoziala;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
	final int DEFAULT_CAPACITY=100;
	int numVertices;
	boolean[][] adjmatrix;
	Pertsona[] vertices;
	
	public int index(String t) {
		int i = 0;
		for (Pertsona p : vertices) {
			if (p.izena.equals(t)) return i;
			i++;
		}
		return 0;
	}
	
	public ArrayList<Pertsona> hurbilak(String p, int adina, int dist){
		ArrayList<Pertsona> emaitza = new ArrayList<Pertsona>();
		int unekoa = index(p);
		boolean[] aztertuak = new boolean[numVertices];
		aztertuak[unekoa] = true;
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		Queue<Integer> unekoAztertuGabeak;
		aztertuGabeak.add(unekoa);
		int i = 0;
		while (i<=dist) {
			unekoAztertuGabeak = aztertuGabeak;
			aztertuGabeak = new LinkedList<Integer>();
			while (!unekoAztertuGabeak.isEmpty()) {
				unekoa = unekoAztertuGabeak.remove();
				if (vertices[unekoa].adina>=adina) emaitza.add(vertices[unekoa]);
				int j = 0;
				for (boolean b : adjmatrix[unekoa]) {
					if (b && !aztertuak[j]) {
						aztertuGabeak.add(j);
						aztertuak[j] = true;
						j++;
					}
				}
			}
			i++;
		}
		return emaitza;
	}
}
