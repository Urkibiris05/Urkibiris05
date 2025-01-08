package BlokeenJokoa;

import java.util.Stack;

public class Jokoa {
	Stack<Bloke>[] taula; // pilen array-a
	public static int ZUTABEKOP=7;
	
	public Jokoa() {
		taula = (Stack<Bloke>[]) new Stack[ZUTABEKOP];
		for (int i = 0; i <= ZUTABEKOP - 1; i++) {
			taula[i] = new Stack<Bloke>();
		}
	}
	
	public int jokatu() {
	// Aurre: jokoa hasieratua izan da (hasierako blokeak sortu dira)
	// Post: partida bat jokatu da. Bola erdiko zutabean hasten da.		
	// Emaitza lortutako puntu-kopurua izango da, jokoa gainditu
	// baldin bada, eta -1 bestela
		int puntuak=0;
		int unekoZutabea=ZUTABEKOP/2;
		while(unekoZutabea>=0 && unekoZutabea<=6 && !taula[unekoZutabea].isEmpty()) {
			Bloke unekoBlokea = taula[unekoZutabea].pop();
			puntuak = puntuak + unekoBlokea.puntuak;
			if (unekoBlokea.norabidea.equals("l")) unekoZutabea = unekoZutabea - unekoBlokea.jauzia;
			else if (unekoBlokea.norabidea.equals("r")) unekoZutabea = unekoZutabea + unekoBlokea.jauzia;
		}
		if (unekoZutabea<0 || unekoZutabea>6) puntuak = -1;
		return puntuak;
	}
}
