package Lurrikara;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear el grafo basado en el diagrama del ejercicio
        Graph grafo = construirGrafo();

        // Imprimir el grafo de manera visual
        System.out.println("Grafo de Lurraldeak:");
        imprimirGrafo(grafo);
        System.out.println();

        // Casos de prueba
        testKaltetutakoak(grafo, 6, "londres", new String[]{
            "londres", "texas", "eibar", "varsovia", 
            "glasgow", "amurrio", "wisconsin", "bilbao", 
            "onati", "paris", "miami", "seattle"
        });
        testKaltetutakoak(grafo, 4, "texas", new String[]{
            "texas", "londres", "glasgow", "eibar", "varsovia"
        });
        testKaltetutakoak(grafo, 3, "bilbao", new String[]{
            "bilbao", "wisconsin", "onati", "eibar"
        });
        testKaltetutakoak(grafo, 2, "seattle", new String[]{
            "seattle"
        });
        testKaltetutakoak(grafo, 1, "berlin", new String[]{
            "berlin"
        });
    }

    private static void testKaltetutakoak(Graph grafo, int intentsitatea, String l, String[] esperotakoak) {
        ArrayList<String> emaitza = grafo.kaltetutakoak(intentsitatea, l);
        System.out.println("Prueba con intensidad " + intentsitatea + " desde " + l);
        System.out.println("Esperado: " + String.join(", ", esperotakoak));
        System.out.println("Obtenido: " + String.join(", ", emaitza));
        if (emaitza.containsAll(java.util.List.of(esperotakoak)) && emaitza.size() == esperotakoak.length) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static Graph construirGrafo() {
        Graph grafo = new Graph();
        grafo.numVertices = 20;
        grafo.vertices = new String[]{
            "bilbao", "nebraska", "washington", "wisconsin", "new york",
            "amurrio", "texas", "onati", "minnesota", "baltimore",
            "glasgow", "londres", "miami", "paris", "eibar",
            "varsovia", "berlin", "burgos", "oslo", "seattle"
        };

        grafo.adjMatrix = new boolean[20][20];
        // Conexiones según el grafo del enunciado
        conectar(grafo, "bilbao", "wisconsin");
        conectar(grafo, "bilbao", "onati");
        conectar(grafo, "wisconsin", "texas");
        conectar(grafo, "wisconsin", "minnesota");
        conectar(grafo, "wisconsin", "washington");
        conectar(grafo, "onati", "eibar");
        conectar(grafo, "texas", "glasgow");
        conectar(grafo, "texas", "londres");
        conectar(grafo, "glasgow", "amurrio");
        conectar(grafo, "glasgow", "seattle");
        conectar(grafo, "londres", "miami");
        conectar(grafo, "londres", "varsovia");
        conectar(grafo, "varsovia", "berlin");
        conectar(grafo, "paris", "miami");
        conectar(grafo, "eibar", "burgos");

        return grafo;
    }

    private static void conectar(Graph grafo, String a, String b) {
        int indexA = grafo.index(a);
        int indexB = grafo.index(b);
        if (indexA != -1 && indexB != -1) { // Verificar que los índices son válidos
            grafo.adjMatrix[indexA][indexB] = true;
            grafo.adjMatrix[indexB][indexA] = true; // Grafo no dirigido
        }
    }

    private static void imprimirGrafo(Graph grafo) {
        for (int i = 0; i < grafo.numVertices; i++) {
            System.out.print(grafo.vertices[i] + " -> ");
            for (int j = 0; j < grafo.numVertices; j++) {
                if (grafo.adjMatrix[i][j]) {
                    System.out.print(grafo.vertices[j] + " ");
                }
            }
            System.out.println();
        }
    }
}

