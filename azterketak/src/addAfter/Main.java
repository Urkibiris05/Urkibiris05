package addAfter;

public class Main {
	    public static void main(String[] args) {
	        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

	        System.out.println("Añadir 10 al inicio de la lista (lista vacía):");
	        list.addAfter(10, 20); // Lista vacía, se añade 10
	        list.printList(); // Esperado: 10

	        System.out.println("Añadir 20 después de 10:");
	        list.addAfter(20, 10);
	        list.printList(); // Esperado: 10 20

	        System.out.println("Añadir 15 después de 10:");
	        list.addAfter(15, 10);
	        list.printList(); // Esperado: 10 15 20

	        System.out.println("Añadir 25 después de 30 (30 no existe, añade al principio):");
	        list.addAfter(25, 30);
	        list.printList(); // Esperado: 25 10 15 20
	    }
}
