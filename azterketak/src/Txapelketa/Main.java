package Txapelketa;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol inicial basado en el ejemplo del enunciado
        Txapelketa txapelketa = new Txapelketa();
        txapelketa.root = construirArbol();

        // Determinar el equipo campeón
        String ganador = txapelketa.txapelduna();

        // Imprimir el equipo campeón
        System.out.println("El equipo campeón es: " + ganador);

        // Verificar que el árbol se actualizó correctamente
        imprimirArbol(txapelketa.root);
    }

    private static Adabegia construirArbol() {
        // Crear nodos hoja
        Adabegia e1 = new Adabegia(new Emaitza("E1", -1, -1), null, null);
        Adabegia e2 = new Adabegia(new Emaitza("E2", -1, -1), null, null);
        Adabegia e3 = new Adabegia(new Emaitza("E3", -1, -1), null, null);
        Adabegia e4 = new Adabegia(new Emaitza("E4", -1, -1), null, null);
        Adabegia e5 = new Adabegia(new Emaitza("E5", -1, -1), null, null);
        Adabegia e6 = new Adabegia(new Emaitza("E6", -1, -1), null, null);
        Adabegia e7 = new Adabegia(new Emaitza("E7", -1, -1), null, null);
        Adabegia e8 = new Adabegia(new Emaitza("E8", -1, -1), null, null);

        // Crear nodos intermedios
        Adabegia n1 = new Adabegia(new Emaitza(null, 1, 0), e1, e2);
        Adabegia n2 = new Adabegia(new Emaitza(null, 2, 0), e3, e4);
        Adabegia n3 = new Adabegia(new Emaitza(null, 1, 3), e5, e6);
        Adabegia n4 = new Adabegia(new Emaitza(null, 1, 0), e7, e8);

        // Crear niveles superiores
        Adabegia n5 = new Adabegia(new Emaitza(null, 0, 2), n1, n2);
        Adabegia n6 = new Adabegia(new Emaitza(null, 2, 0), n3, n4);

        // Crear la raíz
        return new Adabegia(new Emaitza(null, 0, 2), n5, n6);
    }

    private static void imprimirArbol(Adabegia nodo) {
        if (nodo == null) {
            return;
        }
        imprimirArbol(nodo.ezker);
        imprimirArbol(nodo.eskuin);
        if (nodo.info.irabazlea != null) {
            System.out.println("Nodo: " + nodo.info.irabazlea +
                               ", Golak Ezker: " + nodo.info.golakEzker +
                               ", Golak Eskuin: " + nodo.info.golakEskuin);
        }
    }
}