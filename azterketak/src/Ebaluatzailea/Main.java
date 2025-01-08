package Ebaluatzailea;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear la tabla hash inicial
        AldagaienHashTaula hashTaula = new AldagaienHashTaula();
        hashTaula.put("j", 5);
        hashTaula.put("x", 3);
        hashTaula.put("contNum", 0);
        hashTaula.put("y", 7);

        // Crear la lista de instrucciones
        LinkedList<Agindua> aginduak = new LinkedList<>();
        aginduak.add(new Agindua("x", "x", "j", "+"));       // x = x + j
        aginduak.add(new Agindua("contNum", "contNum", "x", "+")); // contNum = contNum + x
        aginduak.add(new Agindua("y", "contNum", "x", "+")); // y = contNum + x

        // Imprimir el estado inicial de la tabla hash
        System.out.println("Estado inicial de la hash-taula:");
        printHashTable(hashTaula);

        // Ejecutar el método ebaluatu
        hashTaula.ebaluatu(aginduak);

        // Imprimir el estado final de la tabla hash
        System.out.println("\nEstado final de la hash-taula:");
        printHashTable(hashTaula);

        // Verificar resultados esperados
        verificarResultado(hashTaula, "j", 5);     // Sin cambios
        verificarResultado(hashTaula, "x", 8);     // x = 3 + 5
        verificarResultado(hashTaula, "contNum", 8); // contNum = 0 + 8
        verificarResultado(hashTaula, "y", 16);    // y = 8 + 8
    }

    private static void printHashTable(AldagaienHashTaula hashTaula) {
        for (String key : hashTaula.keySet()) {
            System.out.println(key + " = " + hashTaula.get(key));
        }
    }

    private static void verificarResultado(AldagaienHashTaula hashTaula, String key, int esperado) {
        int obtenido = hashTaula.get(key);
        System.out.println("\nVerificando: " + key);
        System.out.println("Esperado: " + esperado);
        System.out.println("Obtenido: " + obtenido);
        if (esperado == obtenido) {
            System.out.println("Resultado CORRECTO.");
        } else {
            System.out.println("Resultado INCORRECTO.");
        }
    }
}

