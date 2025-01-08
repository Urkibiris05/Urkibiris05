package Portua;

import java.util.Queue;
import java.util.Stack;

public class Portua {
	Stack<String>[] nasak;
	
	public void portuaSimulatu(Queue<Ontzia> ontziak, int maxEskaera, int nasaKop) {
		//Aurre: maxEskaera deskargako ontzi batek txanda batean kudeatu ditzakeen eskaera kopurua da (>=1)
		//Aurre: nasaKop portuaren nasa kopurua da (>=2). 0. nasa berezia da.
		//Post: portuaren jarduera simulatu da, ontzien eskaerak
		
		//Nasak sortu
		nasak = (Stack<String>[]) new Stack[nasaKop];
		for (int i=0; i<nasaKop; i++) {
			nasak[i]=new Stack<String>();
		}
		
		//Portuaren jarduera simulatu
		while (!ontziak.isEmpty()) {
			Ontzia unekoa = ontziak.remove();
			if (unekoa.mota==0) {
				int kont = 0;
				while (!unekoa.eskaerak.isEmpty() && kont<maxEskaera) {
					Eskaera unekoEskaera = unekoa.eskaerak.remove();
					nasak[unekoEskaera.nasa].push(unekoEskaera.kontainerKode);
					kont++;
				}
				if (!unekoa.eskaerak.isEmpty()) ontziak.add(unekoa);
			} else {
				while (!unekoa.eskaerak.isEmpty()) {
					Eskaera unekoEskaera = unekoa.eskaerak.remove();
					boolean kargatuta = false;
					while (!kargatuta) {
						String kendutakoa = nasak[unekoEskaera.nasa].pop();
						if (kendutakoa.equals(unekoEskaera.kontainerKode)) kargatuta=true;
						else nasak[0].push(kendutakoa);
					} 
					while (!nasak[0].isEmpty()) {
						nasak[unekoEskaera.nasa].push(nasak[0].pop());
					}
				}
			}
		}
	}	
}
