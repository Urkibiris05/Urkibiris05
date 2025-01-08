package Konpilazioa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Konpiladorea {
	HashMap<String, ArrayList<String>> mendekotasunak;
	
	public boolean zuzenaDa(String prog) {
		boolean zuzena = true;
		Queue<String> aztertuGabeak = new LinkedList<String>();
		HashSet<String> aztertuak = new HashSet<String>();
		aztertuGabeak.add(prog);
		aztertuak.add(prog);
		while(zuzena && !aztertuGabeak.isEmpty()) {
			String unekoa = aztertuGabeak.remove();
			if (!mendekotasunak.containsKey(unekoa)) zuzena=false;
			else{
				for (String ondokoa: mendekotasunak.get(unekoa)) {
					if (!aztertuak.contains(ondokoa)) {
						aztertuGabeak.add(ondokoa);
						aztertuak.add(ondokoa);
					}
				}
			}
		}
		return zuzena;
	}
}
