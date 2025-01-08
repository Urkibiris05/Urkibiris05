package PokerPartida;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase Joko
        Joko joko = new Joko();

        // Caso 1: Caso básico con 2 jugadores y 1 billete inicial
        ArrayList<Ordainketa> ordainketak1 = new ArrayList<>();
        ordainketak1.add(new Ordainketa(1, 0, 1)); // El jugador 1 paga 1 billete al jugador 0
        int emaitza1 = joko.jokatu(2, 1, ordainketak1);
        System.out.println("Caso 1 - Resultado esperado: 1, Resultado obtenido: " + emaitza1);

        // Caso 2: Más jugadores, sin transacciones
        ArrayList<Ordainketa> ordainketak2 = new ArrayList<>();
        int emaitza2 = joko.jokatu(5, 3, ordainketak2);
        System.out.println("Caso 2 - Resultado esperado: 0, Resultado obtenido: " + emaitza2);

        // Caso 3: Varias transacciones entre jugadores
        ArrayList<Ordainketa> ordainketak3 = new ArrayList<>();
        ordainketak3.add(new Ordainketa(1, 0, 2)); // Jugador 1 paga 2 billetes al jugador 0
        ordainketak3.add(new Ordainketa(2, 1, 1)); // Jugador 2 paga 1 billete al jugador 1
        ordainketak3.add(new Ordainketa(0, 2, 1)); // Jugador 0 paga 1 billete al jugador 2
        int emaitza3 = joko.jokatu(3, 3, ordainketak3);
        System.out.println("Caso 3 - Resultado esperado: 1, Resultado obtenido: " + emaitza3);

        // Caso 4: Mínimo número de jugadores (2) y varias transacciones
        ArrayList<Ordainketa> ordainketak4 = new ArrayList<>();
        ordainketak4.add(new Ordainketa(1, 0, 1)); // Jugador 1 paga 1 billete al jugador 0
        ordainketak4.add(new Ordainketa(0, 1, 1)); // Jugador 0 paga 1 billete al jugador 1
        int emaitza4 = joko.jokatu(2, 1, ordainketak4);
        System.out.println("Caso 4 - Resultado esperado: 0, Resultado obtenido: " + emaitza4);

        // Caso 5: Número máximo de jugadores (20) y sin transacciones
        ArrayList<Ordainketa> ordainketak5 = new ArrayList<>();
        int emaitza5 = joko.jokatu(20, 5, ordainketak5);
        System.out.println("Caso 5 - Resultado esperado: 0, Resultado obtenido: " + emaitza5);

        // Caso 6: Todas las transacciones posibles entre todos los jugadores
        ArrayList<Ordainketa> ordainketak6 = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            ordainketak6.add(new Ordainketa(i, 0, 2)); // Todos pagan 2 billetes al jugador 0
        }
        int emaitza6 = joko.jokatu(20, 5, ordainketak6);
        System.out.println("Caso 6 - Resultado esperado: 38, Resultado obtenido: " + emaitza6);

        // Caso 7: Transacción con billetes falsos
        ArrayList<Ordainketa> ordainketak7 = new ArrayList<>();
        ordainketak7.add(new Ordainketa(1, 0, 2)); // Jugador 1 paga 2 billetes al jugador 0
        int emaitza7 = joko.jokatu(2, 3, ordainketak7);
        System.out.println("Caso 7 - Resultado esperado: 2, Resultado obtenido: " + emaitza7);
    }
}