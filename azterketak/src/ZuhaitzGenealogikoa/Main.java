package ZuhaitzGenealogikoa;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol genealógico y el mapa de herriak
        ZuhaitzGenealogikoa zuhaitza = new ZuhaitzGenealogikoa();
        zuhaitza.root = construirArbolGenealogiko();

        HashMap<String, String> herriak = new HashMap<>();
        herriak.put("Derio", "Bizkaia");
        herriak.put("Bakio", "Bizkaia");
        herriak.put("Bilbo", "Bizkaia");
        herriak.put("Donostia", "Gipuzkoa");
        herriak.put("Gasteiz", "Araba");

        // Casos de prueba
        testBizkaitarPetoaDa(zuhaitza, "Borja", herriak, true); // Borja y ancestros en Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Carlos", herriak, true); // Carlos es hoja, nacido en Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Fermin", herriak, false); // Fermin tiene ancestros fuera de Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Lupe", herriak, false); // Lupe tiene ancestros fuera de Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Ane", herriak, false); // Ane tiene ancestros fuera de Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Ines", herriak, true); // Ines y sus ancestros están en Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "Kasto", herriak, true); // Kasto es hoja, nacido en Bizkaia
        testBizkaitarPetoaDa(zuhaitza, "NoExiste", herriak, false); // Nodo inexistente
    }

    private static void testBizkaitarPetoaDa(ZuhaitzGenealogikoa zuhaitza, String izena, HashMap<String, String> herriak, boolean expected) {
        boolean result = zuhaitza.bizkaitarPetoaDa(izena, herriak);
        System.out.println("Prueba para: " + izena);
        System.out.println("Esperado: " + expected);
        System.out.println("Resultado: " + result);
        if (result == expected) {
            System.out.println("El resultado es CORRECTO.\n");
        } else {
            System.out.println("El resultado es INCORRECTO.\n");
        }
    }

    private static BinaryTreeNode<Pertsona> construirArbolGenealogiko() {
        // Crear nodos de las personas
        BinaryTreeNode<Pertsona> carlos = new BinaryTreeNode<>(new Pertsona("Carlos", "Derio"), null, null);
        BinaryTreeNode<Pertsona> dina = new BinaryTreeNode<>(new Pertsona("Dina", "Bakio"), null, null);
        BinaryTreeNode<Pertsona> gonztal = new BinaryTreeNode<>(new Pertsona("Gonztal", "Bakio"), null, null);
        BinaryTreeNode<Pertsona> horacio = new BinaryTreeNode<>(new Pertsona("Horacio", "Bilbo"), null, null);
        BinaryTreeNode<Pertsona> kasto = new BinaryTreeNode<>(new Pertsona("Kasto", "Bilbo"), null, null);
        BinaryTreeNode<Pertsona> ines = new BinaryTreeNode<>(new Pertsona("Ines", "Bilbo"), kasto, null);
        BinaryTreeNode<Pertsona> jone = new BinaryTreeNode<>(new Pertsona("Jone", "Bilbo"), ines, null);
        BinaryTreeNode<Pertsona> fermin = new BinaryTreeNode<>(new Pertsona("Fermin", "Donostia"), gonztal, null);
        BinaryTreeNode<Pertsona> lupe = new BinaryTreeNode<>(new Pertsona("Lupe", "Gasteiz"), fermin, null);
        BinaryTreeNode<Pertsona> borja = new BinaryTreeNode<>(new Pertsona("Borja", "Derio"), carlos, dina);
        BinaryTreeNode<Pertsona> elene = new BinaryTreeNode<>(new Pertsona("Elene", "Bilbo"), horacio, jone);

        BinaryTreeNode<Pertsona> ane = new BinaryTreeNode<>(new Pertsona("Ane", "Derio"), borja, elene);
        



        // Nodo raíz
        return ane;
    }
}
