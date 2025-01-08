package inprimagailuak;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Creamos una instancia de la clase Inprimagailuak
        Inprimagailuak inprimagailuak = new Inprimagailuak();
        
        // Creamos la lista de eventos (eskaerak)
        ArrayList<Eskaera> lista = new ArrayList<>();
        
        // Casos de prueba según las posibles casuísticas:
        
        // 1. Añadir trabajos a los inprimagailuak (P)
        lista.add(crearEskaera('P', 1, 5));  // Trabajo 5 a Impresora 1
        lista.add(crearEskaera('P', 2, 6));  // Trabajo 6 a Impresora 2
        lista.add(crearEskaera('P', 3, 7));  // Trabajo 7 a Impresora 3
        
        // 2. Finalizar un trabajo de un inprimagailu específico (B)
        lista.add(crearEskaera('B', 2, null));  // Finalizar el trabajo en Impresora 2
        
        // 3. Itzalketa (I): Redistribuir todos los trabajos a Impresora 0
        lista.add(crearEskaera('I', null, null));
        
        // 4. Añadir más trabajos durante la itzalketa
        lista.add(crearEskaera('P', 4, 8));  // Trabajo 8 debería ir a Impresora 0 por itzalketa
        
        // 5. Finalizar la itzalketa con una zuzenketa (Z)
        lista.add(crearEskaera('Z', null, null));
        
        // 6. Añadir un nuevo trabajo después de la itzalketa
        lista.add(crearEskaera('P', 5, 9));  // Trabajo 9 a Impresora 5
        
        // 7. Probar impresoras vacías y trabajos no existentes
        lista.add(crearEskaera('B', 4, null));  // Finalizar trabajo en Impresora 4 (vacía)

        // Llamar al método para procesar las solicitudes
        System.out.println("Procesando las solicitudes...");
        inprimagailuak.lanakProzesatu(lista);
    }

    /**
     * Método auxiliar para crear una solicitud (Eskaera)
     * @param gertaeraMota Tipo de evento ('P', 'B', 'I', 'Z')
     * @param inprimagailua Número de impresora (puede ser null para 'I' y 'Z')
     * @param lana Número de trabajo (solo aplicable a 'P')
     * @return Un objeto Eskaera configurado
     */
    private static Eskaera crearEskaera(Character gertaeraMota, Integer inprimagailua, Integer lana) {
        Eskaera eskaera = new Eskaera();
        eskaera.gertaeraMota = gertaeraMota;
        eskaera.inprimagailua = inprimagailua;
        eskaera.lana = lana;
        return eskaera;
    }
}