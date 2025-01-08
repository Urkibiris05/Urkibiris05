package BZBngehitu;

public class Main {
    public static void main(String[] args) {
        BZB arbol = new BZB();

        // Añadir nodos al árbol
        arbol.add(53);
        arbol.add(30);
        arbol.add(72);
        arbol.add(14);
        arbol.add(35);
        arbol.add(61);
        arbol.add(84);
        arbol.add(23);
        arbol.add(58);
        arbol.add(65);

        arbol.inOrderTraversal(); // Esperado: 14, 23, 30, 35, 53, 58, 61, 65, 72, 84
        // Probar el método printTartea
        System.out.println("Árbol construido:");
        System.out.println("14-53");
        arbol.printTartea(14, 35); // Esperado: 14, 23, 30, 35
        System.out.println("");
        System.out.println("30-72");
        arbol.printTartea(30, 72); // Esperado: 30, 35, 53, 58, 61, 65, 72
        System.out.println("");
        System.out.println("58-84");
        arbol.printTartea(58, 84); // Esperado: 58, 61, 65, 72, 84
    }
}
