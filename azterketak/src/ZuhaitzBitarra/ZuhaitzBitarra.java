package ZuhaitzBitarra;

import java.util.ArrayList;

public class ZuhaitzBitarra {
	Nodo root;
	
	public 	ArrayList<Integer> lortuBeheranzkoZerrendaOrdenatua(){
		//aurre: zuhaitza BZB bat da	
		//post: emaitzak zuhaitzeko balioak izango ditu, beheranzko ordenean
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lortuBeheranzkoZerrendaOrdenatua(this.root, lista);
		return lista;
	}
	
	private void lortuBeheranzkoZerrendaOrdenatua(Nodo n, ArrayList<Integer> lista){
		if (n==null) return;
		else {
			lortuBeheranzkoZerrendaOrdenatua(n.right, lista);
			lista.add(n.balioa);
			lortuBeheranzkoZerrendaOrdenatua(n.left, lista);
		}
	}
	
	public void zuhaitzaItxi() {
		zuhaitzaItxi(root);
	}
	
	private void zuhaitzaItxi(Nodo n) {
		if (n==null) return;
		if (n.left!=null) zuhaitzaItxi(n.left);
		else {
			Nodo berria = new Nodo();
			berria.balioa=-1;
			berria.zuriaAlaGorria=false;
			n.left=berria;
		}
		if (n.right!=null) zuhaitzaItxi(n.right);
		else {
			Nodo berria = new Nodo();
			berria.balioa=-1;
			berria.zuriaAlaGorria=false;
			n.right=berria;
		}
	}
}
