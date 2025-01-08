package Eskola;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Inicializar la escuela con sus cursos y alumnos
        Eskola eskola = crearEskolaConIkasleak();

        // Caso 1: Prueba básica con un número de regalos múltiplo del número de cursos
        Queue<String> opariakCaso1 = new LinkedList<>();
        for (int i = 1; i <= 12; i++) {
            opariakCaso1.add("r" + i);
        }
        System.out.println("Prueba 1: Regalos múltiplo del número de cursos");
        eskola.opariakBanatu(opariakCaso1);

        // Caso 2: Prueba con más regalos que alumnos totales
        Queue<String> opariakCaso2 = new LinkedList<>();
        for (int i = 1; i <= 20; i++) {
            opariakCaso2.add("r" + i);
        }
        System.out.println("\nPrueba 2: Más regalos que alumnos totales");
        eskola.opariakBanatu(opariakCaso2);

        // Caso 3: Prueba con menos regalos que alumnos en el primer curso
        Queue<String> opariakCaso3 = new LinkedList<>();
        opariakCaso3.add("r1");
        opariakCaso3.add("r2");
        System.out.println("\nPrueba 3: Menos regalos que alumnos en el primer curso");
        eskola.opariakBanatu(opariakCaso3);

        // Caso 4: Prueba con lista de regalos vacía
        Queue<String> opariakCaso4 = new LinkedList<>();
        System.out.println("\nPrueba 4: Lista de regalos vacía");
        eskola.opariakBanatu(opariakCaso4);

        // Caso 5: Prueba con una escuela sin alumnos
        Eskola eskolaVacia = new Eskola();
        eskolaVacia.ikaslea = new LinkedList<>();
        Queue<String> opariakCaso5 = new LinkedList<>();
        opariakCaso5.add("r1");
        System.out.println("\nPrueba 5: Escuela sin alumnos");
        eskolaVacia.opariakBanatu(opariakCaso5);
    }

    /**
     * Crea una escuela con 6 cursos y varios alumnos por curso.
     * @return Una instancia de Eskola con sus cursos y alumnos inicializados.
     */
    private static Eskola crearEskolaConIkasleak() {
        Eskola eskola = new Eskola();
        eskola.ikaslea = new LinkedList<>();

        // Añadir los cursos con sus alumnos
        eskola.ikaslea.add(crearCurso("a", "b", "c", "d")); // Primer curso
        eskola.ikaslea.add(crearCurso("i", "j", "k", "l")); // Segundo curso
        eskola.ikaslea.add(crearCurso("p", "q", "r", "s")); // Tercer curso
        eskola.ikaslea.add(crearCurso("w", "x", "y", "z")); // Cuarto curso
        eskola.ikaslea.add(crearCurso("m", "n", "o", "p")); // Quinto curso
        eskola.ikaslea.add(crearCurso("u", "v", "w", "x")); // Sexto curso

        return eskola;
    }

    /**
     * Crea un curso con una lista de alumnos.
     * @param alumnos Nombres de los alumnos.
     * @return Una cola que representa el curso con sus alumnos.
     */
    private static Queue<String> crearCurso(String... alumnos) {
        Queue<String> curso = new LinkedList<>();
        for (String alumno : alumnos) {
            curso.add(alumno);
        }
        return curso;
    }
}