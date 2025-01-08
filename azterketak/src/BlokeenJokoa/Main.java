package BlokeenJokoa;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Jokoa
        Jokoa jokoa = new Jokoa();

        // Caso 1: Partida con bloques en cada columna
        prepararTabla(jokoa, new String[][] {
            {"10,l,1", "20,r,2"}, // Columna 0
            {"15,l,2"},           // Columna 1
            {"25,r,3"},           // Columna 2
            {"30,l,1"},           // Columna 3
            {"5,r,2"},            // Columna 4
            {},                   // Columna 5 (vacía desde el inicio)
            {"10,l,1", "5,r,1"}   // Columna 6
        });
        System.out.println("Puntuak (Caso 1): " + jokoa.jokatu()); // Verificar puntaje

        // Caso 2: Jokoa gaindituta (columna termina vacía)
        prepararTabla(jokoa, new String[][] {
            {"10,r,7"}  // Movimiento fuera del rango desde el primer bloque
        });
        System.out.println("Puntuak (Caso 2): " + jokoa.jokatu()); // Esperado: puntuación válida

        // Caso 3: Jokoa galduta (bola fuera del tablero)
        prepararTabla(jokoa, new String[][] {
            {"10,l,7"}  // Movimiento fuera del rango hacia la izquierda
        });
        System.out.println("Puntuak (Caso 3): " + jokoa.jokatu()); // Esperado: -1

        // Caso 4: Sin bloques en la columna inicial
        prepararTabla(jokoa, new String[][] {
            {}, // Vacío (columna inicial)
        });
        System.out.println("Puntuak (Caso 4): " + jokoa.jokatu()); // Esperado: puntuación válida

        // Caso 5: Prueba de múltiples bloques con combinaciones
        prepararTabla(jokoa, new String[][] {
            {"5,l,1", "10,r,2"},
            {"20,r,3"},
            {"15,l,1"},
            {"25,r,2"},
            {"10,l,3"},
            {"5,r,1"},
            {"30,l,4"}
        });
        System.out.println("Puntuak (Caso 5): " + jokoa.jokatu()); // Depende de la configuración
    }

    /**
     * Configura el tablero de bloques según los datos proporcionados.
     * @param jokoa Instancia del juego.
     * @param configuracion Configuración de bloques, cada array representa una columna.
     */
    private static void prepararTabla(Jokoa jokoa, String[][] configuracion) {
        for (int i = 0; i < Jokoa.ZUTABEKOP; i++) {
            jokoa.taula[i].clear(); // Limpiar la columna
            if (i < configuracion.length) {
                for (String bloqueData : configuracion[i]) {
                    String[] partes = bloqueData.split(",");
                    int puntuak = Integer.parseInt(partes[0]);
                    String norabidea = partes[1];
                    int jauzia = Integer.parseInt(partes[2]);
                    Bloke bloke = new Bloke();
                    bloke.puntuak = puntuak;
                    bloke.norabidea = norabidea;
                    bloke.jauzia = jauzia;
                    jokoa.taula[i].push(bloke); // Añadir bloque
                }
            }
        }
    }
}

