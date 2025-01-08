package Epidemia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Pertsona {
	String izena;
	boolean kutsatuta;
	ArrayList<Pertsona> kontaktuak;
	
	public boolean kutsatu() {
		return false;
	}
	
	public void epidemiarenSimulazioa() {
		Queue<Pertsona> aztertuGabeak = new LinkedList<Pertsona>();
		HashSet<Pertsona> aztertuak = new HashSet<Pertsona>();
		aztertuGabeak.add(this);
		aztertuak.add(this);
		while (!aztertuGabeak.isEmpty()) {
			Pertsona unekoa = aztertuGabeak.remove();
			for (Pertsona p: unekoa.kontaktuak) {
				if (!aztertuak.contains(p)) {
					aztertuak.add(p);
					if (p.kutsatu()) {
						aztertuGabeak.add(p);
						p.kutsatuta=true;
					}
				}
			}
		}
	}
}
