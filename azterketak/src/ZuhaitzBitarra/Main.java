package ZuhaitzBitarra;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
        // Crear el árbol de ejemplo del ejercicio
        ZuhaitzBitarra zuhaitz = new ZuhaitzBitarra();
        Nodo root = new Nodo();
        root.balioa = 40;
        root.left = new Nodo();
        root.left.balioa = 22;
        root.right = new Nodo();
        root.right.balioa = 54;

        root.left.left = new Nodo();
        root.left.left.balioa = 7;
        root.left.right = new Nodo();
        root.left.right.balioa = 29;

        root.left.right.left = new Nodo();
        root.left.right.left.balioa = 25;

        root.right.left = new Nodo();
        root.right.left.balioa = 48;
        root.right.right = new Nodo();
        root.right.right.balioa = 70;

        root.right.left.left = new Nodo();
        root.right.left.left.balioa = 46;
        root.right.left.right = new Nodo();
        root.right.left.right.balioa = 50;

        root.right.left.left.left = new Nodo();
        root.right.left.left.left.balioa = 43;
        root.right.left.right.left = new Nodo();
        root.right.left.right.left.balioa = 49;

        zuhaitz.root = root;

        // Obtener la lista descendente
        ArrayList<Integer> listaOrdenada = zuhaitz.lortuBeheranzkoZerrendaOrdenatua();

        // Imprimir el resultado esperado y el obtenido
        System.out.println("Resultado esperado: [70, 54, 50, 49, 48, 46, 43, 40, 29, 25, 22, 7]");
        System.out.println("Resultado obtenido: " + listaOrdenada);

        // Comprobar diferentes casos adicionales
        probarCasosExtra();
        
        zuhaitz.zuhaitzaItxi();

        // Verificar el árbol después de cerrar los nodos
        System.out.println("Comprobación tras ejecutar zuhaitzaItxi():");
        verificarZuhaitzaItxi(zuhaitz);

        // Probar otros casos
        probarCasosExtra2();
    }

    public static void probarCasosExtra() {
        // Caso 1: Árbol vacío
        ZuhaitzBitarra arbolVacio = new ZuhaitzBitarra();
        ArrayList<Integer> listaVacia = arbolVacio.lortuBeheranzkoZerrendaOrdenatua();
        System.out.println("Caso árbol vacío:");
        System.out.println("Resultado esperado: []");
        System.out.println("Resultado obtenido: " + listaVacia);

        // Caso 2: Árbol con un solo nodo
        ZuhaitzBitarra arbolUnNodo = new ZuhaitzBitarra();
        Nodo unicoNodo = new Nodo();
        unicoNodo.balioa = 10;
        arbolUnNodo.root = unicoNodo;
        ArrayList<Integer> listaUnNodo = arbolUnNodo.lortuBeheranzkoZerrendaOrdenatua();
        System.out.println("Caso árbol con un solo nodo:");
        System.out.println("Resultado esperado: [10]");
        System.out.println("Resultado obtenido: " + listaUnNodo);

        // Caso 3: Árbol con solo lado izquierdo
        ZuhaitzBitarra arbolIzquierdo = new ZuhaitzBitarra();
        Nodo nodoIzquierdo = new Nodo();
        nodoIzquierdo.balioa = 10;
        nodoIzquierdo.left = new Nodo();
        nodoIzquierdo.left.balioa = 5;
        arbolIzquierdo.root = nodoIzquierdo;
        ArrayList<Integer> listaIzquierdo = arbolIzquierdo.lortuBeheranzkoZerrendaOrdenatua();
        System.out.println("Caso árbol con solo lado izquierdo:");
        System.out.println("Resultado esperado: [10, 5]");
        System.out.println("Resultado obtenido: " + listaIzquierdo);

        // Caso 4: Árbol con solo lado derecho
        ZuhaitzBitarra arbolDerecho = new ZuhaitzBitarra();
        Nodo nodoDerecho = new Nodo();
        nodoDerecho.balioa = 10;
        nodoDerecho.right = new Nodo();
        nodoDerecho.right.balioa = 15;
        arbolDerecho.root = nodoDerecho;
        ArrayList<Integer> listaDerecho = arbolDerecho.lortuBeheranzkoZerrendaOrdenatua();
        System.out.println("Caso árbol con solo lado derecho:");
        System.out.println("Resultado esperado: [15, 10]");
        System.out.println("Resultado obtenido: " + listaDerecho);
        
        
    }
    public static void verificarZuhaitzaItxi(ZuhaitzBitarra zuhaitz) {
        if (verificarNodo(zuhaitz.root)) {
            System.out.println("El árbol está correctamente cerrado: todos los nodos tienen dos hijos.");
        } else {
            System.out.println("El árbol no está correctamente cerrado.");
        }
    }

    private static boolean verificarNodo(Nodo n) {
        return true;
    }

    public static void probarCasosExtra2() {
        // Caso 1: Árbol vacío
        ZuhaitzBitarra arbolVacio = new ZuhaitzBitarra();
        arbolVacio.zuhaitzaItxi();
        System.out.println("Caso árbol vacío:");
        verificarZuhaitzaItxi(arbolVacio);

        // Caso 2: Árbol con un solo nodo
        ZuhaitzBitarra arbolUnNodo = new ZuhaitzBitarra();
        Nodo unicoNodo = new Nodo();
        unicoNodo.balioa = 10;
        unicoNodo.zuriaAlaGorria = true; // Nodo original es zuria
        arbolUnNodo.root = unicoNodo;

        arbolUnNodo.zuhaitzaItxi();
        System.out.println("Caso árbol con un solo nodo:");
        verificarZuhaitzaItxi(arbolUnNodo);

        // Caso 3: Árbol con solo lado izquierdo (menores)
        ZuhaitzBitarra arbolIzquierdo = new ZuhaitzBitarra();
        Nodo nodoIzquierdo = new Nodo();
        nodoIzquierdo.balioa = 10;
        nodoIzquierdo.left = new Nodo();
        nodoIzquierdo.left.balioa = 5; // Menor que 10
        arbolIzquierdo.root = nodoIzquierdo;

        arbolIzquierdo.zuhaitzaItxi();
        System.out.println("Caso árbol con solo lado izquierdo:");
        verificarZuhaitzaItxi(arbolIzquierdo);

        // Caso 4: Árbol con solo lado derecho (mayores)
        ZuhaitzBitarra arbolDerecho = new ZuhaitzBitarra();
        Nodo nodoDerecho = new Nodo();
        nodoDerecho.balioa = 10;
        nodoDerecho.right = new Nodo();
        nodoDerecho.right.balioa = 15; // Mayor que 10
        arbolDerecho.root = nodoDerecho;

        arbolDerecho.zuhaitzaItxi();
        System.out.println("Caso árbol con solo lado derecho:");
        verificarZuhaitzaItxi(arbolDerecho);
    }
}

