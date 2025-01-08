package KontakturikLagungarriena;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KontaktuSarea {
	private boolean[][] adjMatrix;
	
	public int lagungarriena(ArrayList<Integer> pertsonak) {
		int[] puntuazioak = new int[adjMatrix.length];
		for (Integer p : pertsonak) {
			Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
			boolean[] aztertuak = new boolean[adjMatrix.length];
			aztertuGabeak.add(p);
			aztertuak[p]=true;
			while(!aztertuGabeak.isEmpty()) {
				Integer unekoa = aztertuGabeak.remove();
				int i = 0;
				for	(boolean kontaktua : adjMatrix[unekoa]) {
					if (kontaktua && !aztertuak[i]) {
						aztertuGabeak.add(i);
						aztertuak[i]=true;
						if (bateragarriakDira(i, p)) puntuazioak[i]++;
					}
					i++;
				}
			}
		}
		int maxPuntuazioa = -1;
		int lagungarriena = -1;
		int i = 0;
		for (int kont : puntuazioak) {
			if (kont>maxPuntuazioa) {
				lagungarriena = i;
				maxPuntuazioa = kont;
			}
			i++;
		}
		return lagungarriena;
	}
	
	private boolean bateragarriakDira(Integer p1, Integer p2) {
		return true;
	}
}
