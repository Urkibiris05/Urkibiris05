package Kaleak;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoKaleak extends GraphAL<String> {
	
	public LinkedList<String> bilatuBidea (String hasiera, String bukaera, LinkedList<String> obratan) {
		LinkedList<String> bidea = new LinkedList<String>();
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		boolean[] aztertuak = new boolean[numVertices];
		HashMap<Integer, Integer> backPointers = new HashMap<Integer, Integer>();
		Integer unekoa = index(hasiera);
		aztertuGabeak.add(unekoa);
		aztertuak[unekoa]=true;
		backPointers.put(unekoa, null);
		boolean aurkitua = false;
		while (!aztertuGabeak.isEmpty() && !aurkitua) {
			unekoa = aztertuGabeak.remove();
			if (vertices[unekoa].equals(bukaera)) aurkitua=true;
			else {
				for (Integer ondokoa: adjList[unekoa]) {
					if (!aztertuak[ondokoa] && !obratan.contains(vertices[ondokoa])) {
						aztertuak[ondokoa]=true;
						aztertuGabeak.add(ondokoa);
						backPointers.put(ondokoa, unekoa);
					}
				}
			}
		}
		if (aurkitua) {
			Stack<Integer> pila = new Stack<Integer>();
			while(unekoa!=null) {
				pila.push(unekoa);
				unekoa = backPointers.get(unekoa);
			}
			while (!pila.isEmpty()) {
				bidea.add(vertices[pila.pop()]);
			}
		}
		return bidea;
	}
}