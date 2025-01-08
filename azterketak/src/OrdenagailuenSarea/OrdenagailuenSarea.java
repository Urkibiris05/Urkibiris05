package OrdenagailuenSarea;

import java.util.LinkedList;
import java.util.Queue;

public class OrdenagailuenSarea {
	protected Boolean[][] adjMatrix;
	
	public int[] kostuakLortu() {
		int[] kostuak = new int[adjMatrix.length];
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		boolean[] aztertuak = new boolean[adjMatrix.length];
		aztertuGabeak.add(0);
		aztertuak[0]=true;
		kostuak[0]=0;
		while (!aztertuGabeak.isEmpty()) {
			Integer unekoa = aztertuGabeak.remove();
			for (int j = 0; j<adjMatrix.length; j++) {
				if (adjMatrix[unekoa][j] && !aztertuak[j]) {
					kostuak[j]=kostuak[unekoa]+1;
					aztertuGabeak.add(j);
					aztertuak[j]=true;
				}
			}
		}
		return kostuak;
	}
}
