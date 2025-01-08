package Eficiencia;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol binario basado en el diagrama
        MiArbol arbol = new MiArbol();
        arbol.root = construirArbol();

        // Lista de prueba 1
        LinkedList<String> lista1 = new LinkedList<>();
        lista1.add("T");
        lista1.add("M");
        lista1.add("E");
        lista1.add("S");
        System.out.println("Prueba 1: Lista {T, M, E, S}");
        double resultado1 = arbol.numMedioDeElementos(lista1);
        System.out.println("Resultado: " + resultado1); // Esperado: 3.0
        System.out.println();

        // Lista de prueba 2
        LinkedList<String> lista2 = new LinkedList<>();
        lista2.add("Q");
        lista2.add("K");
        lista2.add("C");
        lista2.add("V");
        System.out.println("Prueba 2: Lista {Q, K, C, V}");
        double resultado2 = arbol.numMedioDeElementos(lista2);
        System.out.println("Resultado: " + resultado2); // Esperado: cálculo específico
        System.out.println();

        // Lista de prueba 3
        LinkedList<String> lista3 = new LinkedList<>();
        lista3.add("M");
        System.out.println("Prueba 3: Lista {M}");
        double resultado3 = arbol.numMedioDeElementos(lista3);
        System.out.println("Resultado: " + resultado3); // Esperado: 1.0
        System.out.println();
    }

    private static BinaryTreeNode<String> construirArbol() {
        BinaryTreeNode<String> m = new BinaryTreeNode<>("M");
        BinaryTreeNode<String> e = new BinaryTreeNode<>("E");
        BinaryTreeNode<String> q = new BinaryTreeNode<>("Q");
        BinaryTreeNode<String> d = new BinaryTreeNode<>("D");
        BinaryTreeNode<String> j = new BinaryTreeNode<>("J");
        BinaryTreeNode<String> o = new BinaryTreeNode<>("O");
        BinaryTreeNode<String> v = new BinaryTreeNode<>("V");
        BinaryTreeNode<String> c = new BinaryTreeNode<>("C");
        BinaryTreeNode<String> f = new BinaryTreeNode<>("F");
        BinaryTreeNode<String> k = new BinaryTreeNode<>("K");
        BinaryTreeNode<String> t = new BinaryTreeNode<>("T");
        BinaryTreeNode<String> s = new BinaryTreeNode<>("S");
        BinaryTreeNode<String> u = new BinaryTreeNode<>("U");

        // Construir el árbol manualmente
        m.left = e;
        m.right = q;

        e.left = d;
        e.right = j;

        d.left = c;

        j.left = f;
        j.right = k;

        q.left = o;
        q.right = v;

        v.left = t;
        t.left = s;
        t.right = u;

        return m;
    }
}
