package AdierazpenarenEbaluazioa;

public class InfoElemExp {
	String elem;	//*,+ edo aldagai baten izena
	boolean eragigaia;	//true-> eragigaia, false -> aldagaia
	
	public InfoElemExp(String pElem, boolean pOp) {
		elem = pElem;
		eragigaia = pOp;
	}
	
	public String toString(){
		return elem;
	}
}
