package TaldeenIlara;

import java.util.LinkedList;
import java.util.Queue;

public class Kafetegia {
	Queue<Queue<Integer>> ilara = null; //ilaren ilara
	
	public void txertatu(Integer elem) {
	//postbaldintza: "elem" gehitu da kafetegiko ilaran 
		if (ilara==null) ilara = new LinkedList<Queue<Integer>>();
		Queue<Queue<Integer>> ilaraAux = new LinkedList<Queue<Integer>>();
		boolean txertatua = false;
		while (!ilara.isEmpty()) {
			Queue<Integer> unekoTaldea = ilara.remove();
			if (taldeBera(elem, unekoTaldea.peek())) {
				unekoTaldea.add(elem);
				txertatua = true;
			}
			ilaraAux.add(unekoTaldea);
		}
		if (!txertatua) {
			Queue<Integer> taldeBerria = new LinkedList<Integer>();
			taldeBerria.add(elem);
			ilaraAux.add(taldeBerria);
		}
		ilara=ilaraAux;
	}
	
	public Integer ezabatu() {
	//Aurre:gutxienez pertsona bad dago ilaran
	//post: ilarako lehen elementua kendu eta bere balioa bueltatu da
		Integer ezabatua = ilara.peek().remove();
		if (ilara.peek().isEmpty()) ilara.remove();
		return ezabatua;
	}
	
	public boolean taldeBera(int p1, int p2) {
	//post: true baldin p1 eta p2 talde berekoak badira eta false bestela
	// EZ DA METODO HAU INPLEMENTATU BEHAR
		return true;
	}
}	
