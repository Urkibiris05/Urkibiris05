package PokerPartida;

import java.util.ArrayList;
import java.util.Stack;

public class Joko {
	
	public int jokatu(int jokalariKop, int hasierakoBilleteKop, ArrayList<Ordainketa> ordainketak) {
		//Aurre: 2<= jokalariKop <= 20
		//		 hasierakoBilleteKop >= 1
		//		 "ordainketak" zerrendako ordainekta guztiak zuzenak dira, hau da, jokalari batek beti izango ditu nahiko 
		//		 billete ordainketa egiteko
		//Post: emaitza, partida amaitzean 0garren jokalariaren billete onen kopurua izango da
		
		Stack<Boolean>[] jokalariak = (Stack<Boolean>[]) new Stack[jokalariKop];
		boolean billeteak;
		for (int i=0; i<jokalariKop; i++) {
			jokalariak[i] = new Stack<Boolean>();
			for (int j=0; j<hasierakoBilleteKop; j++) {
				if (i==0) billeteak=false;
				else billeteak=true;
				jokalariak[i].push(billeteak);
			}
		}
		for (Ordainketa unekoOrdainketa : ordainketak) {
			for (int k=0; k<unekoOrdainketa.kopurua; k++) {
				jokalariak[unekoOrdainketa.kobratzailea].push(jokalariak[unekoOrdainketa.ordaintzailea].pop());
			}
		}
		int kont=0;
		while (!jokalariak[0].isEmpty()) {
			if (jokalariak[0].pop()) kont++;
		}
		return kont;
	}
}
