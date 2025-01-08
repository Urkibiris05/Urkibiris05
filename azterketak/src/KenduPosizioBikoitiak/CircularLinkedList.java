package KenduPosizioBikoitiak;

public class CircularLinkedList<T> {
	Node<T> first;
	
	public CircularLinkedList<T> kenduElementuBikoitiak(){
	//post: posizio bikoitietan dauden elementuak kendu dira zerrendatik
	//		kendutako elementuen zerrenda zirkular bat bueltatuko da
		/*
		CircularLinkedList<T> kendutakoElementuak = new CircularLinkedList<>();
		Node<T> lag = first;
		Node<T> lag2 = kendutakoElementuak.first;
		int kont = 1;
		do {
			Node<T> hurrengoa = lag.next;
			if (kont%2 == 0) {
				lag.prev.next=lag.next;
				lag.next.prev=lag.prev;
				if (lag2==null) {
					kendutakoElementuak.first=lag;
					lag2 = lag;
					lag2.next=lag2;
					lag2.prev=lag2;
				} else {
					lag.prev=lag2;
					lag.next=kendutakoElementuak.first;
					lag2.next=lag;
					kendutakoElementuak.first.prev=lag;
					lag2=lag;
				}
			}
			lag=hurrengoa;
			kont++;
		} while(lag!=first);
		return kendutakoElementuak;
		*/
		
	    CircularLinkedList<T> lista = new CircularLinkedList<>();
	    Node<T> unekoa = first;
	    Node<T> lag = null;
	    Node<T> ezabatua = null;
	    int i=1;
	    do {
	        if (i%2 == 0) {
	            ezabatua = unekoa;
	            unekoa.prev.next=unekoa.next;
	            unekoa.next.prev=unekoa.prev;
	            unekoa=unekoa.next;
	            if (lista.first==null){
	                lista.first=ezabatua;
	                lista.first.prev=lista.first;
	                lista.first.next=lista.first;
	            } else {
	                ezabatua.prev=lag;
	                ezabatua.next=lag.next;
	                lag.next.prev=ezabatua;
	                lag.next=ezabatua;
	            }
	            lag=ezabatua;
	        } else{
	            unekoa=unekoa.next;
	        }
	        i++;
	    } while (unekoa!=first);
	    return lista;
	}
	
	public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (first == null) {
            first = newNode;
            first.next = first;
            first.prev = first;
        } else {
            Node<T> last = first.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = first;
            first.prev = newNode;
        }
    }
	
	public void printList() {
        if (first == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        
        Node<T> current = first;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != first);
        System.out.println();
    }
	
	public static void main(String[] args) {
	        CircularLinkedList<Integer> lista = new CircularLinkedList<>();
	        
	        // Agregamos algunos elementos a la lista
	        lista.insertLast(1);
	        lista.insertLast(2);
	        lista.insertLast(3);
	        lista.insertLast(4);
	        lista.insertLast(5);
	        lista.insertLast(6);
	        
	        System.out.println("Lista original:");
	        lista.printList();
	        
	        // Llamamos a kenduElementuBikoitiak
	        CircularLinkedList<Integer> eliminados = lista.kenduElementuBikoitiak();
	        
	        System.out.println("Lista después de eliminar elementos en posiciones pares:");
	        lista.printList();
	        
	        System.out.println("Elementos eliminados:");
	        eliminados.printList();
	 }
}
