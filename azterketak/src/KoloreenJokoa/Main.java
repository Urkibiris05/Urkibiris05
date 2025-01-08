package KoloreenJokoa;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase Jokoa
        Jokoa juego = new Jokoa();

        // Número inicial de fichas por jugador
        int fichasIniciales = 5;

        // Definir las tiradas para las pruebas
        ArrayList<Jokaldi> tiradas;

        // Prueba 1: El juego termina inmediatamente cuando el dado 1 es 6
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(6, 3)); // Termina inmediatamente
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 1, "Prueba 1: Termina con dado 6");

        // Prueba 2: Movimiento de ficha negra desde el jugador 0 a la mesa
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 (banca) mueve una ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 3)); // Jugador 2 recoge ficha negra de la mesa
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 2, "Prueba 2: Movimiento ficha negra al jugador 2");

        // Prueba 3: Movimiento de varias fichas y verificar el ganador con más fichas negras
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 mueve ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 2)); // Jugador 1 recoge ficha negra de la mesa
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 mueve ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 3)); // Jugador 2 recoge ficha negra de la mesa
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 mueve ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 3)); // Jugador 2 recoge ficha negra de la mesa
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 2, "Prueba 3: Jugador 2 gana con más fichas negras");

        // Prueba 4: Empate en fichas negras, gana el jugador con prioridad
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 mueve ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 2)); // Jugador 1 recoge ficha negra de la mesa
        tiradas.add(new Jokaldi(4, 1)); // Jugador 0 mueve ficha negra a la mesa
        tiradas.add(new Jokaldi(3, 3)); // Jugador 2 recoge ficha negra de la mesa
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 1, "Prueba 4: Empate, gana jugador 1 por prioridad");

        // Prueba 5: Sin movimientos válidos
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(1, 1)); // Dado impar, pero mesa vacía
        tiradas.add(new Jokaldi(3, 5)); // Dado impar, pero mesa vacía
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 1, "Prueba 5: Sin movimientos válidos");

        // Prueba 6: Verificar múltiples jugadores moviendo fichas a la mesa
        tiradas = new ArrayList<>();
        tiradas.add(new Jokaldi(2, 2)); // Jugador 1 mueve ficha a la mesa
        tiradas.add(new Jokaldi(2, 3)); // Jugador 2 mueve ficha a la mesa
        tiradas.add(new Jokaldi(2, 4)); // Jugador 3 mueve ficha a la mesa
        verificarGanador(juego.jokoa(fichasIniciales, tiradas), 1, "Prueba 6: Múltiples jugadores moviendo fichas");
    }

    /**
     * Verifica si el ganador obtenido coincide con el esperado.
     */
    public static void verificarGanador(int ganadorObtenido, int ganadorEsperado, String descripcionPrueba) {
        if (ganadorObtenido == ganadorEsperado) {
            System.out.println(descripcionPrueba + " -> PASÓ (Ganador: " + ganadorObtenido + ")");
        } else {
            System.err.println(descripcionPrueba + " -> FALLÓ (Esperado: " + ganadorEsperado + ", Obtenido: " + ganadorObtenido + ")");
        }
    }
}

