package nkoiztu;

public class Lista<T> {
    Node<T> first;

   public void multiplicar(Integer n) {
    // pre: n >= 1
    // post: la lista ha sido modificada y contiene cada elemento de la
    // lista inicial repetido “n” veces

        if (first==null) return;
        Node<T> unekoa = first;
        do{
            for (int i = 1; i<n; i++){
                Node<T> berria = new Node<>(unekoa.data);
                berria.prev=unekoa;
                berria.next=unekoa.next;
                unekoa.next.prev=berria;
                unekoa.next=berria;
                unekoa=berria;
            }
            unekoa=unekoa.next;

        } while(unekoa!=first);
    }
   
   public static void main(String[] args) {
       Lista<Integer> lista = new Lista<>();

       // Añadir elementos a la lista
       lista.add(1);
       lista.add(2);
       lista.add(3);

       // Imprimir la lista original
       System.out.println("Lista original:");
       lista.printList();

       // Multiplicar cada elemento por 3
       lista.multiplicar(2);

       // Imprimir la lista después de la multiplicación
       System.out.println("Lista después de multiplicar cada elemento por 3:");
       lista.printList();
   }
   
   public void add(T data) {
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
       if (first == null) return;

       Node<T> current = first;
       do {
           System.out.print(current.data + " ");
           current = current.next;
       } while (current != first);
       System.out.println();
   }
}
