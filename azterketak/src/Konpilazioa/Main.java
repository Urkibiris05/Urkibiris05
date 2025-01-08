package Konpilazioa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto Konpiladorea con las dependencias del ejemplo
        Konpiladorea konpiladorea = new Konpiladorea();
        konpiladorea.mendekotasunak = new HashMap<>();
        
        // Añadir las dependencias al mapa
        konpiladorea.mendekotasunak.put("PR1", new ArrayList<>(List.of("PR2", "PR3")));
        konpiladorea.mendekotasunak.put("PR2", new ArrayList<>(List.of("PR5", "PR3")));
        konpiladorea.mendekotasunak.put("PR3", new ArrayList<>(List.of("PR5")));
        konpiladorea.mendekotasunak.put("PR5", new ArrayList<>(List.of("PR2")));
        konpiladorea.mendekotasunak.put("PR14", new ArrayList<>(List.of("PR25")));
        konpiladorea.mendekotasunak.put("PR15", new ArrayList<>(List.of("PR5", "PR14")));

        // Pruebas
        realizarPrueba(konpiladorea, "PR1", true);  // PR1 es correcto
        realizarPrueba(konpiladorea, "PR15", false); // PR15 depende de PR25, que no está definido
        realizarPrueba(konpiladorea, "PR3", true);  // PR3 es correcto
        realizarPrueba(konpiladorea, "PR14", false); // PR14 depende de PR25, que no está definido
        realizarPrueba(konpiladorea, "PR5", true);  // PR5 es correcto
        realizarPrueba(konpiladorea, "PR25", false); // PR25 no está definido en la tabla
    }

    private static void realizarPrueba(Konpiladorea konpiladorea, String programa, boolean esperado) {
        boolean resultado = konpiladorea.zuzenaDa(programa);
        System.out.println("Programa: " + programa);
        System.out.println("Esperado: " + esperado);
        System.out.println("Resultado: " + resultado);
        if (resultado == esperado) {
            System.out.println("Resultado CORRECTO\n");
        } else {
            System.out.println("Resultado INCORRECTO\n");
        }
    }
}
