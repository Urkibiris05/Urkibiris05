package NotaHoberenak;

import java.util.HashMap;
import java.util.LinkedList;

public class Ikasleak extends HashMap<String, IrakasgaienZuhaitza> {
	
	public LinkedList<String> lortuNotaHoberenekoIkasleak(LinkedList<String> l){
		HashMap<String, Integer> notak = new HashMap<String, Integer>();
		Integer notaMax = -1;
		for (String ikasle: l) {
			Integer unekoNota = this.get(ikasle).notaHoberena();
			if (unekoNota>notaMax) notaMax=unekoNota;
			notak.put(ikasle, unekoNota);
		}
		LinkedList<String> emaitza = new LinkedList<String>();
		for (String ikasle: notak.keySet()) {
			Integer unekoNota = notak.get(ikasle);
			if (unekoNota == notaMax) emaitza.add(ikasle);
		}
		return emaitza;
	}
}
