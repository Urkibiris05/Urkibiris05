package JokoarenZuhaitza;

public class Zuhaitza {
	private Nodo root;

	public Zuhaitza() {
		Nodo n1 = new Nodo(new Info("B", 3));
		Nodo n2 = new Nodo(new Info("C", 3));
		Nodo n3 = new Nodo(new Info("D", 3));
		Nodo n4 = new Nodo(new Info("E", 3));
		Nodo n5 = new Nodo(new Info("F", 3));
		Nodo n6 = new Nodo(new Info("G", 3));
		Nodo n7 = new Nodo(new Info("H", 3));

		root = n1;
		n1.izq = n2;
		n2.padre = n1;
		n1.der = n3;
		n3.padre = n1;
		n2.izq = n4;
		n4.padre = n2;
		n2.der = n5;
		n5.padre = n2;
		n3.izq = n6;
		n6.padre = n3;
		n3.der = n7;
		n7.padre = n3;
	}

	public void print(){
		print(root);
	}
	
	private void print(Nodo n){
		if (n != null)
			{ print(n.izq);
			  n.content.print();
			  print(n.der);
			}
	}
	
	public void premiar(int puntos, String elem) {
		//aurre: elementua zerrendan dago. 
		premiar(puntos, elem, root, false);
	}

	private void premiar(int puntos, String elem, Nodo n, boolean aurkitua) {
		if (n==null) {}
		else {
			if (n.content.s.equals(elem)) {
				aurkitua=true;
				banatuPuntuak(puntos, n);
			} 
			if (!aurkitua) premiar(puntos, elem, n.izq, aurkitua);
			if (!aurkitua) premiar(puntos, elem, n.der, aurkitua);
			
		}

	}
	
	private void banatuPuntuak(int puntos, Nodo n) {
		n.content.puntos = n.content.puntos + puntos;
		if (n.padre!=null) banatuPuntuak(puntos-1, n.padre);	
	}

	private class Info {
		String s;
		Integer puntos;

		public Info(String nom, int p) {
			s = nom;
			puntos = p;
		}
		
		public void print(){
			System.out.print(" " + s + " " + puntos + ", ");
		}
	}

	private class Nodo {
		Info content;
		Nodo izq, der;
		Nodo padre;

		public Nodo(Info i) {
			content = i;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Zuhaitza a = new Zuhaitza();
		a.print();
		System.out.println();
		a.premiar(8, "G");
		a.print();
	}
}
