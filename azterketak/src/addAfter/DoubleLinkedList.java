package addAfter;

public class DoubleLinkedList<T> {
	DoubleNode<T> first;
	
	public void addAfter(T elem, T target){
	// Precondición:
	// Postcondición: se ha insertado el elemento “elem” detrás del elemento “target”,
	// En caso de que no existiera un elemento igual a “target”, se
	// insertará el nuevo elemento al principio de la lista
		
		DoubleNode<T> unekoa = first;
		boolean txertatua = false;
		DoubleNode<T> berria = new DoubleNode<T>();
		berria.data=elem;
		do {
			if (unekoa==null) {
				first=berria;
				berria.next=berria;
				berria.prev=berria;
				txertatua=true;
			} else if (unekoa.data.equals(target)) {
				berria.prev=unekoa;
				berria.next=unekoa.next;
				unekoa.next.prev=berria;
				unekoa.next=berria;
				txertatua=true;
			} else {
				unekoa=unekoa.next;
			}
		} while (unekoa!=first && !txertatua);
		if (!txertatua) {
			berria.next=first;
			berria.prev=first.prev;
			first.prev.next=berria;
			first.prev=berria;
			first=berria;
		}
	}
	
    public void printList() {
        if (first == null) {
            System.out.println("La lista está vacía");
            return;
        }
        DoubleNode<T> current = first;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != first);
        System.out.println();
    }
}
