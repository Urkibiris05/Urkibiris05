package inprimagailuak;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Inprimagailuak {

	public void lanakProzesatu(ArrayList<Eskaera> lista) {
		//inprimagailuak hasieratu
		Queue<Integer>[] inprimagailuak = (LinkedList<Integer>[]) new LinkedList[6];
		for (int i=0; i<6; i++) {
			inprimagailuak[i] = new LinkedList<Integer>();
		}
		//eskaerak prozesatu
		boolean itzalketa = false;
		for (Eskaera unekoEskaera : lista) {
			switch (unekoEskaera.gertaeraMota) {
				case 'P':
					if (!itzalketa) inprimagailuak[unekoEskaera.inprimagailua].add(unekoEskaera.lana);
					else inprimagailuak[0].add(unekoEskaera.lana);
					break;
				case 'B':
					if (!inprimagailuak[unekoEskaera.inprimagailua].isEmpty()) {
						inprimagailuak[unekoEskaera.inprimagailua].remove();
					} 
					break;
				case 'I':
					itzalketa = true;
					for (int j=1; j<6; j++) {
						while (!inprimagailuak[j].isEmpty()) {
							inprimagailuak[0].add(inprimagailuak[j].remove());
						}
					}
					break;
				case 'Z':
					itzalketa = false;
					break;
			}
		}
		//inprimagailuen egoera inprimatu
		for (int k=0; k<6; k++) {
			System.out.print("Imp "+k+": ");
			if (inprimagailuak[k].isEmpty()) System.out.print("hutsik");
			else {
				while (!inprimagailuak[k].isEmpty()) {
					System.out.print(inprimagailuak[k].remove() + " ");
				}
			}
			System.out.println("");
		}
	}
}
