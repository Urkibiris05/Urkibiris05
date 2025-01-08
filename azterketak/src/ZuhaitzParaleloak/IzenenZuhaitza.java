package ZuhaitzParaleloak;

import java.util.ArrayList;

public class IzenenZuhaitza {
	Nodo rootAbizenak;
	Nodo rootIzenak;
	
	public String lortuIzena(String abizena) {
		return lortuIzena(abizena, rootAbizenak, rootIzenak);
	}
	private String lortuIzena(String abizena, Nodo nAbizenak, Nodo nIzenak) {
		if (nAbizenak==null) return null;
		else if (nAbizenak.balioa.equals(nIzenak.balioa)) return nIzenak.balioa;
		else {
			String izenaEzk = lortuIzena(abizena, nAbizenak.left, nIzenak.left);
			if (izenaEzk==null) return lortuIzena(abizena, nAbizenak.right, nIzenak.right);
			else return izenaEzk;
		}
	}
	public ArrayList<String> zerrendaOrdenatuaLortu(){
		ArrayList<String> emaitza = new ArrayList<String>();
		zerrendaOrdenatuaLortu(emaitza, rootAbizenak, rootIzenak);
		return emaitza;
	}
	private void zerrendaOrdenatuaLortu(ArrayList<String> emaitza, Nodo nAbizenak, Nodo nIzenak) {
		if (nAbizenak==null) {}
		else {
			zerrendaOrdenatuaLortu(emaitza, nAbizenak.left, nIzenak.left);
			emaitza.add(nAbizenak.balioa+", "+nIzenak.balioa);
			zerrendaOrdenatuaLortu(emaitza, nAbizenak.right, nIzenak.left);
		}
	}
}
