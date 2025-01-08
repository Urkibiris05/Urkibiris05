package SareSoziala2;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Caso 1: Grafo vacío
        Grafo<String> grafoVacio = new Grafo<>();
        grafoVacio.num_vertices = 0;
        grafoVacio.adjList = (LinkedList<Integer>[]) new LinkedList[0];
        testZerrenda2Matrize(grafoVacio, new boolean[0][0]);
        testMatrize2Zerrenda(grafoVacio, new LinkedList[0]);

        // Caso 2: Grafo con un solo nodo y sin aristas
        Grafo<String> grafoUnitario = new Grafo<>();
        grafoUnitario.num_vertices = 1;
        grafoUnitario.adjList = (LinkedList<Integer>[]) new LinkedList[1];
        grafoUnitario.adjList[0] = new LinkedList<>();
        testZerrenda2Matrize(grafoUnitario, new boolean[][]{{false}});
        testMatrize2Zerrenda(grafoUnitario, grafoUnitario.adjList);

        // Caso 3: Grafo con múltiples nodos sin aristas
        Grafo<String> grafoSinAristas = new Grafo<>();
        grafoSinAristas.num_vertices = 3;
        grafoSinAristas.adjList = (LinkedList<Integer>[]) new LinkedList[3];
        for (int i = 0; i < 3; i++) {
            grafoSinAristas.adjList[i] = new LinkedList<>();
        }
        testZerrenda2Matrize(grafoSinAristas, new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        });
        testMatrize2Zerrenda(grafoSinAristas, grafoSinAristas.adjList);

        // Caso 4: Grafo con una arista entre cada par de nodos
        Grafo<String> grafoCompleto = new Grafo<>();
        grafoCompleto.num_vertices = 3;
        grafoCompleto.adjList = (LinkedList<Integer>[]) new LinkedList[3];
        for (int i = 0; i < 3; i++) {
            grafoCompleto.adjList[i] = new LinkedList<>();
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    grafoCompleto.adjList[i].add(j);
                }
            }
        }
        testZerrenda2Matrize(grafoCompleto, new boolean[][]{
                {false, true, true},
                {true, false, true},
                {true, true, false}
        });
        testMatrize2Zerrenda(grafoCompleto, grafoCompleto.adjList);

        // Caso 5: Grafo dirigido con algunas aristas
        Grafo<String> grafoDirigido = new Grafo<>();
        grafoDirigido.num_vertices = 4;
        grafoDirigido.adjList = (LinkedList<Integer>[]) new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            grafoDirigido.adjList[i] = new LinkedList<>();
        }
        grafoDirigido.adjList[0].add(1);
        grafoDirigido.adjList[1].add(2);
        grafoDirigido.adjList[2].add(3);
        testZerrenda2Matrize(grafoDirigido, new boolean[][]{
                {false, true, false, false},
                {false, false, true, false},
                {false, false, false, true},
                {false, false, false, false}
        });
        testMatrize2Zerrenda(grafoDirigido, grafoDirigido.adjList);
    }

    private static void testZerrenda2Matrize(Grafo<String> grafo, boolean[][] expectedMatrix) {
        System.out.println("Prueba zerrenda2Matrize:");
        grafo.zerrenda2Matrize();
        System.out.println("Matriz generada:");
        printAdjMatrix(grafo.adjMatrix);
        System.out.println("Matriz esperada:");
        printAdjMatrix(expectedMatrix);
        if (compareMatrices(grafo.adjMatrix, expectedMatrix)) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static void testMatrize2Zerrenda(Grafo<String> grafo, LinkedList<Integer>[] expectedList) {
        System.out.println("Prueba matrize2Zerrenda:");
        grafo.matrize2Zerrenda();
        System.out.println("Lista generada:");
        printAdjList(grafo.adjList);
        System.out.println("Lista esperada:");
        printAdjList(expectedList);
        if (compareAdjLists(grafo.adjList, expectedList)) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static void printAdjMatrix(boolean[][] matrix) {
        if (matrix == null) {
            System.out.println("La matriz de adyacencia es nula.");
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    private static void printAdjList(LinkedList<Integer>[] list) {
        if (list == null) {
            System.out.println("La lista de adyacencia es nula.");
            return;
        }
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + ": ");
            for (Integer adj : list[i]) {
                System.out.print(adj + " ");
            }
            System.out.println();
        }
    }

    private static boolean compareMatrices(boolean[][] matrix1, boolean[][] matrix2) {
        if (matrix1.length != matrix2.length) return false;
        for (int i = 0; i < matrix1.length; i++) {
            if (matrix1[i].length != matrix2[i].length) return false;
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) return false;
            }
        }
        return true;
    }

    private static boolean compareAdjLists(LinkedList<Integer>[] list1, LinkedList<Integer>[] list2) {
        if (list1.length != list2.length) return false;
        for (int i = 0; i < list1.length; i++) {
            if (!list1[i].equals(list2[i])) return false;
        }
        return true;
    }
}
