package KostuarenKalkulua;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol de prueba
        ZereginenZuhaitza zuhaitza = new ZereginenZuhaitza();
        zuhaitza.root = construirArbol();
        zuhaitza.printTree();

        // Caso 1: Zeregina T31 (hoja)
        testKostua(zuhaitza, "T31", 3);

        // Caso 2: Zeregina T12 (subárbol)
        testKostua(zuhaitza, "T12", 23);

        // Caso 3: Zeregina T7 (subárbol completo)
        testKostua(zuhaitza, "T7", 72);

        // Caso 4: Zeregina T34 (hoja)
        testKostua(zuhaitza, "T34", 7);

        // Caso 5: Zeregina T45 (subárbol pequeño)
        testKostua(zuhaitza, "T45", 8);

        // Caso 6: Nodo inexistente
        testKostua(zuhaitza, "T99", 0);

        // Caso 7: Árbol vacío
        ZereginenZuhaitza arbolVacio = new ZereginenZuhaitza();
        testKostua(arbolVacio, "T1", 0);
    }

    private static void testKostua(ZereginenZuhaitza zuhaitza, String id, int expectedCost) {
        int actualCost = zuhaitza.kostua(id);
        System.out.println("Prueba para zeregina: " + id);
        System.out.println("Kostua esperado: " + expectedCost);
        System.out.println("Kostua obtenido: " + actualCost);
        if (actualCost == expectedCost) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static BinaryTreeNode construirArbol() {
        // Construir manualmente el árbol representado en la imagen
        BinaryTreeNode t21 = new BinaryTreeNode(new Zeregin("T21", 8), null, null);
        BinaryTreeNode t45 = new BinaryTreeNode(new Zeregin("T45", 8), null, null);
        BinaryTreeNode t31 = new BinaryTreeNode(new Zeregin("T31", 3), null, null);
        BinaryTreeNode t34 = new BinaryTreeNode(new Zeregin("T34", 7), null, null);
        BinaryTreeNode t10 = new BinaryTreeNode(new Zeregin("T10", 6), null, null);

        BinaryTreeNode t54 = new BinaryTreeNode(new Zeregin("T54", 12), t45, t21);
        BinaryTreeNode t12 = new BinaryTreeNode(new Zeregin("T12", 3), t10, t34);

        BinaryTreeNode t7 = new BinaryTreeNode(new Zeregin("T7", 5), t54, t31);
        BinaryTreeNode root = new BinaryTreeNode(new Zeregin("T1", 5), t7, t12);
        return root;
    }
}
