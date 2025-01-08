package KoloreenJokoa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Jokoa {
	Queue<Integer>[] jokalariak;
	// Fitxen koloreak balio osokoen bidez adierazten dira: beltzak 0 eta
	// beste jokalarien kolorea bere posizioarekin bat etorriko da (hau da,
	// 1 jokalariak 1 kolorea, ...)
	Stack<Integer> mahaia;
	public int jokoa(int n, ArrayList<Jokaldi> jokaldiak) {
	// aurre: n jokalari bakoitzaren hasierako fitxa-kopurua da
	// “jokaldiak” zerrendak partida bateko jokaldiak ditu
	// post: emaitza irabazlearen zenbakia da
		//haiseraketak
		mahaia = new Stack<Integer>();
		jokalariak = (LinkedList<Integer>[]) new LinkedList[6];
		for (int i=0; i<6; i++) {
			jokalariak[i] = new LinkedList<Integer>();
			for (int j=0; j<n; j++) {
				jokalariak[i].add(i);
			}
		}
		
		//partida
		boolean amaituta=false;
		int i = 0;
		while(i<jokaldiak.size() && !amaituta){
			Jokaldi unekoJokaldia = jokaldiak.get(i);
			if (unekoJokaldia.dado1==6) amaituta=true;
			if (unekoJokaldia.dado1%2==0) {
				if (!jokalariak[unekoJokaldia.dado2-1].isEmpty()) {
					mahaia.push(jokalariak[unekoJokaldia.dado2-1].remove());
				}
			} else {
				if(!mahaia.isEmpty()) {
					jokalariak[unekoJokaldia.dado2-1].add(mahaia.pop());
				}
			}
			i++;
		}
		int irabazlea=1;
		int unekoP;
		int puntuazioMax=-1;
		for (int j = 1; j<jokalariak.length; j++) {
			unekoP=0;
			while(!jokalariak[j].isEmpty()) {
				if (jokalariak[j].remove()==0) unekoP++;
			}
			if (unekoP>puntuazioMax) {
				puntuazioMax=unekoP;
				irabazlea=j;
			}
		}
		return irabazlea;
	}
}
