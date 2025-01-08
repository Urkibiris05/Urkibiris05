package Eskola;

import java.util.LinkedList;
import java.util.Queue;

public class Eskola {
	Queue<Queue<String>> ikaslea; //ilaren ilara
	
	public void opariakBanatu(Queue<String> opariak) {
		if (ikaslea.isEmpty()) return;
		Queue<Queue<String>> ikasleaAux = new LinkedList<Queue<String>>();
		while (!opariak.isEmpty()) {
			if(ikaslea.isEmpty()) {
				ikaslea=ikasleaAux;
				ikasleaAux = new LinkedList<Queue<String>>();
			}
			Queue<String> ilaraAux = new LinkedList<String>();
			Queue<String> unekoIkasturtea = ikaslea.remove();
			String unekoOparia = opariak.remove();
			while (!unekoIkasturtea.isEmpty()) {
				String unekoIkaslea = unekoIkasturtea.remove();
				System.out.println("("+unekoIkaslea+","+unekoOparia+")");
				ilaraAux.add(unekoIkaslea);
			}
			ikasleaAux.add(ilaraAux);
		}
		while (!ikaslea.isEmpty()) {
			ikasleaAux.add(ikaslea.remove());
		}
		ikaslea=ikasleaAux;
	}
}
