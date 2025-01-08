package Jokoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoJokoa {
	private HashMap<Integer, ArrayList<Laukia>> ondokoak;
	
	public LinkedList<Laukia> bilatuBidea(Laukia hasiera, Laukia bukaera){
		LinkedList<Laukia> emaitza = new LinkedList<Laukia>();
		Queue<Laukia> aztertuGabeak = new LinkedList<Laukia>();
		HashSet<Laukia> aztertuak = new HashSet<Laukia>();
		HashMap<Laukia, Laukia> backPointers = new HashMap<Laukia, Laukia>();
		aztertuGabeak.add(hasiera);
		aztertuak.add(hasiera);
		backPointers.put(hasiera, null);
		boolean bidea = true;
		while(!aztertuGabeak.isEmpty() && bidea) {
			Laukia unekoa = aztertuGabeak.remove();
			if (unekoa.equals(bukaera)) bidea=true;
			else {
				ArrayList<Laukia> senideak = ondokoak.get(unekoa.balioa);
				for (Laukia l: senideak) {
					if (!l.kolorea.equals(unekoa.kolorea)&&!aztertuak.contains(l)) {
						aztertuGabeak.add(l);
						aztertuak.add(l);
						backPointers.put(l, unekoa);
					}
				}
			}
		}
		if (bidea) {
			Stack<Laukia> pila = new Stack<Laukia>();
			pila.push(bukaera);
			Laukia aurrekoa = backPointers.get(bukaera);
			while (aurrekoa!=null) {
				pila.push(aurrekoa);
				aurrekoa=backPointers.get(aurrekoa);
			}
			while(!pila.isEmpty()) {
				emaitza.add(pila.pop());
			}
		}
		return emaitza;
	}
		
}
