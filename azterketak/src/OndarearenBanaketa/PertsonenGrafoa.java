package OndarearenBanaketa;

import java.util.LinkedList;
import java.util.Queue;

public class PertsonenGrafoa extends GraphAL<Pertsona> {
	
	public int banatu(int kantitatea, int n, Pertsona p) {
		int unekoa = index(p);
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		Queue<Integer> unekoAztertuGabeak;
		boolean[] aztertuak = new boolean[numVertices];
		aztertuGabeak.add(unekoa);
		aztertuak[unekoa]=true;
		int d=0;
		int kopurua=0;
		while (d<n) {
			unekoAztertuGabeak = aztertuGabeak;
			aztertuGabeak = new LinkedList<Integer>();
			while(!unekoAztertuGabeak.isEmpty()) {
				unekoa=unekoAztertuGabeak.remove();
				for (Integer ahaidea : adjList[unekoa]) {
					if (!aztertuak[ahaidea]) {
						kopurua++;
						aztertuak[ahaidea]=true;
						aztertuGabeak.add(ahaidea);
					}
				}
			}
			d++;
		}
		return kantitatea/kopurua;
	}
}
