package Spam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Pertsona {
	String izena;
	int spamJasotakoak;
	ArrayList<Pertsona> kontaktuak;
	
	public void spamBidali() {
		Queue<Pertsona> aztertuGabeak = new LinkedList<Pertsona>();
		HashSet<Pertsona> bidaliDute = new HashSet<Pertsona>();
		aztertuGabeak.add(this);
		while(!aztertuGabeak.isEmpty()) {
			Pertsona unekoa = aztertuGabeak.remove();
			if (!bidaliDute.contains(unekoa)) {
				bidaliDute.add(unekoa);
				for(Pertsona p : kontaktuak) {
					if(p.berbidali())aztertuGabeak.add(p);
					p.spamJasotakoak++;
				}
			}
		}
	}
	
	public boolean berbidali() {
		return true;
	}
}
