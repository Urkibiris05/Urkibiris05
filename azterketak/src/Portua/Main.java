package Portua;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Crear instancia de Portua
        Portua portua = new Portua();

        // Crear casos de prueba
        Queue<Ontzia> ontziak = new LinkedList<>();
        
        // Caso 1: Desgarga con múltiples contenedores a diferentes nasas
        Ontzia ontziDeskargako = new Ontzia();
        ontziDeskargako.mota = 0; // Desgarga
        ontziDeskargako.izena = "Ontzia1";
        ontziDeskargako.eskaerak = new LinkedList<>();
        ontziDeskargako.eskaerak.add(new Eskaera("P76", 1)); // Contenedor para nasa 1
        ontziDeskargako.eskaerak.add(new Eskaera("J12", 2)); // Contenedor para nasa 2
        ontziDeskargako.eskaerak.add(new Eskaera("P19", 1)); // Otro contenedor para nasa 1
        ontziak.add(ontziDeskargako);

        // Caso 2: Carga, intentando recoger contenedores de diferentes nasas
        Ontzia ontziKargako = new Ontzia();
        ontziKargako.mota = 1; // Carga
        ontziKargako.izena = "Ontzia2";
        ontziKargako.eskaerak = new LinkedList<>();
        ontziKargako.eskaerak.add(new Eskaera("P76", 1)); // Intentar recoger de nasa 1
        ontziKargako.eskaerak.add(new Eskaera("J12", 2)); // Intentar recoger de nasa 2
        ontziak.add(ontziKargako);

        // Caso 3: Desgarga con un límite de maxEskaera
        Ontzia ontziDeskargaLimitada = new Ontzia();
        ontziDeskargaLimitada.mota = 0; // Desgarga
        ontziDeskargaLimitada.izena = "Ontzia3";
        ontziDeskargaLimitada.eskaerak = new LinkedList<>();
        ontziDeskargaLimitada.eskaerak.add(new Eskaera("X45", 3)); // Contenedor para nasa 3
        ontziDeskargaLimitada.eskaerak.add(new Eskaera("Y87", 3)); // Otro contenedor para nasa 3
        ontziDeskargaLimitada.eskaerak.add(new Eskaera("Z90", 4)); // Contenedor para nasa 4
        ontziak.add(ontziDeskargaLimitada);

        // Simular el puerto
        int maxEskaera = 2; // Límite de 2 contenedores por operación de descarga
        int nasaKop = 5; // Número de nasas (incluyendo 0 como especial)

        System.out.println("Comenzando simulación del puerto...");
        portua.portuaSimulatu(ontziak, maxEskaera, nasaKop);

        // Verificar el estado de las nasas tras la simulación
        System.out.println("Estado final de las nasas:");
        for (int i = 0; i < nasaKop; i++) {
            System.out.println("Nasa " + i + ": " + portua.nasak[i]);
        }

        // Asegurarnos de que todas las operaciones se ejecutaron correctamente
        assert portua.nasak[1].contains("P76") == false : "Error: P76 no debería estar en nasa 1";
        assert portua.nasak[2].contains("J12") == false : "Error: J12 no debería estar en nasa 2";
        assert portua.nasak[3].contains("X45") == false : "Error: X45 no debería estar en nasa 3";
        assert portua.nasak[4].contains("Z90") == false : "Error: Z90 no debería estar en nasa 4";

        System.out.println("¡Simulación finalizada sin errores!");
    }
}
