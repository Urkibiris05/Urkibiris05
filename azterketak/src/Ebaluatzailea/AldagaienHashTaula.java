package Ebaluatzailea;

import java.util.HashMap;
import java.util.LinkedList;

public class AldagaienHashTaula extends HashMap<String, Integer>{
	
	public void ebaluatu(LinkedList<Agindua> aginduak) {
		for (Agindua a: aginduak) {
			Integer y = this.get(a.ize2);
			Integer z = this.get(a.ize3);
			switch(a.eragile) {
				case "+":
					this.put(a.ize1, y+z);
					break;
				case "-":
					this.put(a.ize1, y-z);
					break;
				case "*":
					this.put(a.ize1, y*z);
					break;
				case "/":
					this.put(a.ize1, y/z);
					break;
			}
		}
	}

}
