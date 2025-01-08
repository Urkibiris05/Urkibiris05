package BilaketaZuhaitzNTarrean;

import java.util.Arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol de ejemplo según el enunciado
        Zuhaitza zuhaitza = new Zuhaitza();
        zuhaitza.root = construirArbol();

        // Imprimir el árbol visualmente
        System.out.println("Árbol visual:");
        imprimirArbol(zuhaitza.root, 0);
        System.out.println();

        // Casos de prueba
        testBadago(zuhaitza, 15, true);  // Nodo raíz
        testBadago(zuhaitza, 1, true);   // Nodo hoja
        testBadago(zuhaitza, 78, true);  // Nodo intermedio
        testBadago(zuhaitza, 92, true);  // Último nodo hoja
        testBadago(zuhaitza, 50, false); // Valor inexistente
        testBadago(zuhaitza, 8, true);   // Nodo intermedio
        testBadago(zuhaitza, 100, false);// Valor mayor que todos
        testBadago(zuhaitza, -1, false); // Valor menor que todos
        testBadago(zuhaitza, 30, true);  // Nodo interno
    }

    private static void testBadago(Zuhaitza zuhaitza, Integer elem, boolean expected) {
        boolean result = zuhaitza.badago(elem);
        System.out.println("Prueba para elemento: " + elem);
        System.out.println("Esperado: " + expected);
        System.out.println("Resultado: " + result);
        if (result == expected) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static BinaryTreeNode<Integer> construirArbol() {
        // Crear nodos hoja
        BinaryTreeNode<Integer> hoja1 = new BinaryTreeNode<>(new Integer[]{1, 4, 7}, new BinaryTreeNode[4]);
        BinaryTreeNode<Integer> hoja2 = new BinaryTreeNode<>(new Integer[]{9, 12, 13}, new BinaryTreeNode[4]);
        BinaryTreeNode<Integer> hoja3 = new BinaryTreeNode<>(new Integer[]{76, 78}, new BinaryTreeNode[3]);
        BinaryTreeNode<Integer> hoja4 = new BinaryTreeNode<>(new Integer[]{81, 83, 85, 87}, new BinaryTreeNode[5]);
        BinaryTreeNode<Integer> hoja5 = new BinaryTreeNode<>(new Integer[]{90, 92}, new BinaryTreeNode[3]);
        BinaryTreeNode<Integer> hoja6 = new BinaryTreeNode<>(new Integer[]{94, 96, 99}, new BinaryTreeNode[4]);

        // Crear nodos intermedios
        BinaryTreeNode<Integer> nodo1 = new BinaryTreeNode<>(new Integer[]{8}, new BinaryTreeNode[]{hoja1, hoja2});
        BinaryTreeNode<Integer> nodo2 = new BinaryTreeNode<>(new Integer[]{18, 20, 30}, new BinaryTreeNode[]{hoja2, hoja3, hoja4, hoja5});
        BinaryTreeNode<Integer> nodo3 = new BinaryTreeNode<>(new Integer[]{49, 52, 67, 72}, new BinaryTreeNode[]{hoja3, hoja4, hoja5, hoja6, null});
        BinaryTreeNode<Integer> nodo4 = new BinaryTreeNode<>(new Integer[]{80, 88}, new BinaryTreeNode[]{hoja4, hoja5, hoja6});

        // Crear nodo raíz
        return new BinaryTreeNode<>(new Integer[]{15, 45, 75, 93}, new BinaryTreeNode[]{nodo1, nodo2, nodo3, nodo4, hoja6});
    }

    private static void imprimirArbol(BinaryTreeNode<Integer> nodo, int nivel) {
        if (nodo == null) return;

        // Imprimir valores del nodo actual
        System.out.println("  ".repeat(nivel) + Arrays.toString(nodo.balioak));

        // Imprimir recursivamente los hijos
        for (BinaryTreeNode<Integer> hijo : nodo.umeak) {
            imprimirArbol(hijo, nivel + 1);
        }
    }
}
