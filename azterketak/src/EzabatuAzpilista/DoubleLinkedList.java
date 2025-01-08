package EzabatuAzpilista;

public class DoubleLinkedList<T> {
	DoubleNode<T> first;

	public void insertLast(T elem) {
		if (first == null) {
			DoubleNode<T> node1 = new DoubleNode<>();
			node1.data = elem;
			node1.next = node1;
			node1.prev = node1;
			first = node1;
		} else {
			DoubleNode<T> node1 = new DoubleNode<>();
			node1.data = elem;
			node1.next = first;
			node1.prev = first.prev;
			first.prev.next = node1;
			first.prev = node1;
		}
	}

	public void print() {
		if (first == null) {
		} else {
			DoubleNode<T> act = first;
			System.out.print(act.data.toString() + " ");
			act = act.next;
			while (act != first) {
				System.out.print(act.data.toString() + " ");
				act = act.next;
			}
			System.out.println();
		}

	}
	
	public void ezabatuLista(DoubleLinkedList<T> azpilista) {
		DoubleNode<T> lag = first;
		boolean ezabatua = false;
		do {
			if (lag.data.equals(azpilista.first.data)){
				boolean firstBorratua = false;
				if (lag==first) firstBorratua=true;
				DoubleNode<T >lag3 = lag.next;
				DoubleNode<T> lag4 = azpilista.first.next;
				boolean aurkitua=true;
				while (lag4!=azpilista.first && aurkitua) {
					aurkitua = lag3.data.equals(lag4.data);
					if (lag3==first) firstBorratua=true;
					lag3=lag3.next;
					lag4=lag4.next;
				}
				if (aurkitua) {
					if (lag==lag3) first=null;
					else {
						lag.prev.next=lag3;
						lag3.prev=lag.prev;
						if (firstBorratua) first=lag3;
						ezabatua=true;
					}
				}
			}
			lag=lag.next;
		} while (lag != first && !ezabatua);
	}

	public static DoubleLinkedList<String> crearLista(String[] s){
		DoubleLinkedList<String> l = new DoubleLinkedList<String>();
		for (int i = 0; i < s.length; i++) {
			l.insertLast(s[i]);
		}
		return l;
	}

	public static void main(String[] args) {
		// Caso 1
		String[] s1 = { "jon", "amaia", "luis" };
		DoubleLinkedList<String> sub = crearLista(s1);
		String[] s2 = {"ana", "jon", "amaia", "luis", "ander"};
		DoubleLinkedList<String> l = crearLista(s2);
		System.out.println("================================================================");
		System.out.println("Caso 1: borrar({jon, amaia, luis}, {ana, jon, amaia, luis, ander})");
		System.out.println("================================================================");
		sub.print();
		l.print();

		l.ezabatuLista(sub);
		l.print();

		// Caso 2: la sublista llega hasta el final de la primera lista y
		// continúa circularmente
		System.out.println("================================================================");
		System.out.println("Caso 2: borrar({luis, ander, ana}, {ana, jon, amaia, luis, ander})");
		System.out.println("================================================================");
		String[] s3 = { "luis", "ander", "ana" };
		sub = crearLista(s3);
		String[] s4 = { "ana", "jon", "amaia", "luis", "ander" };
		l = crearLista(s4);

		sub.print();
		l.print();

		l.ezabatuLista(sub);
		l.print();

		// Caso 3: la sublista es igual a la lista
		System.out.println("================================================================");
		System.out.println("Caso 3: borrar({ana, jon, amaia, luis, ander}, {ana, jon, amaia, luis, ander})");
		System.out.println("================================================================");
		String[] s5 = { "ana", "jon", "amaia", "luis", "ander" };
		sub = crearLista(s5);
		String[] s6 = { "ana", "jon", "amaia", "luis", "ander" };
		l = crearLista(s6);

		sub.print();
		l.print();

		l.ezabatuLista(sub);
		l.print();

	}

}