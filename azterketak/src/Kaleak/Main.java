package Kaleak;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear el grafo
        GrafoKaleak grafo = construirGrafo();

        // Prueba 1: Ruta de "A" a "G" sin obras
        LinkedList<String> obratan = new LinkedList<>();
        System.out.println("Prueba 1: Ruta de A a G sin obras");
        LinkedList<String> resultado1 = grafo.bilatuBidea("A", "G", obratan);
        System.out.println("Resultado: " + resultado1); // Esperado: [A, B, D, E, G]

        // Prueba 2: Ruta de "A" a "G" con "C" en obras
        obratan.add("C");
        System.out.println("\nPrueba 2: Ruta de A a G con C en obras");
        LinkedList<String> resultado2 = grafo.bilatuBidea("A", "G", obratan);
        System.out.println("Resultado: " + resultado2); // Esperado: [A, B, D, E, G]

        // Prueba 3: Ruta de "A" a "I" con "B" y "D" en obras
        obratan.clear();
        obratan.add("B");
        obratan.add("D");
        System.out.println("\nPrueba 3: Ruta de A a I con B y D en obras");
        LinkedList<String> resultado3 = grafo.bilatuBidea("A", "I", obratan);
        System.out.println("Resultado: " + resultado3); // Esperado: []

        // Prueba 4: Ruta inexistente entre "H" y "C"
        obratan.clear();
        System.out.println("\nPrueba 4: Ruta de H a C");
        LinkedList<String> resultado4 = grafo.bilatuBidea("H", "C", obratan);
        System.out.println("Resultado: " + resultado4); // Esperado: []

        // Prueba 5: Ruta de "A" a "H" sin obras
        System.out.println("\nPrueba 5: Ruta de A a H sin obras");
        LinkedList<String> resultado5 = grafo.bilatuBidea("A", "H", obratan);
        System.out.println("Resultado: " + resultado5); // Esperado: [A, B, D, F, H]
    }

    private static GrafoKaleak construirGrafo() {
        GrafoKaleak grafo = new GrafoKaleak();
        grafo.numVertices = 10; // Número de vértices
        grafo.vertices = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        grafo.adjList = new LinkedList[grafo.numVertices];
        for (int i = 0; i < grafo.numVertices; i++) {
            grafo.adjList[i] = new LinkedList<>();
        }

        // Conexiones basadas en el diagrama
        conectar(grafo, "A", "B");
        conectar(grafo, "A", "C");
        conectar(grafo, "B", "D");
        conectar(grafo, "C", "F");
        conectar(grafo, "D", "E");
        conectar(grafo, "E", "G");
        conectar(grafo, "F", "G");
        conectar(grafo, "F", "H");
        conectar(grafo, "E", "J");
        conectar(grafo, "H", "I");

        return grafo;
    }

    private static void conectar(GrafoKaleak grafo, String desde, String hasta) {
        int indexDesde = grafo.index(desde);
        int indexHasta = grafo.index(hasta);
        grafo.adjList[indexDesde].add(indexHasta);
    }
}
