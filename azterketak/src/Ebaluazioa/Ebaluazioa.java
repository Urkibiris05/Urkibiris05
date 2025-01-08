package Ebaluazioa;

import java.util.Stack;

public class Ebaluazioa {
	public double ebaluatu(String esp) {
		// Aurrebaldintza: Espresio aritmetikoa zuzena da.
		// Adierazpena GUZTIZ parentizatuta dago, hau da,
		// Eragile bakoitzeko, parentesiak daude
		// Eragile guztiak bitarrak dira (“X ERAGILE Y” motakoak)
		// Postbaldintza: emaitza adierazpenaren balioa da
		double emaitza = 0;
		Stack<Character> eragileak = new Stack<Character>();
		Stack<Integer> eragigaiak = new Stack<Integer>(); 
		int i=0;
		while (i<esp.length()) {
			Character c = esp.charAt(i);
			if (c.equals('+') || c.equals('*')) eragileak.push(c);
			else if (c.equals('(')||c.equals(' '))	{}
			else if (c.equals(')')) {
				Character eragilea = eragileak.pop();
				if (eragilea.equals('+')) eragigaiak.push(eragigaiak.pop()+eragigaiak.pop());
				else eragigaiak.push(eragigaiak.pop()*eragigaiak.pop());
			} else eragigaiak.push(Integer.parseInt(c.toString()));
			
		}
		
		return emaitza;
	}
}
