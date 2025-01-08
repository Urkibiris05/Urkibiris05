package NotaHoberenak;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear el mapa de ikasleak y sus notas
        Ikasleak ikasleak = new Ikasleak();
        ikasleak.put("Jose Rodriguez", crearIrakasgaienZuhaitza(new int[]{7, 8, 2}, new String[]{"EDA", "ALG", "PRO"}));
        ikasleak.put("Ana Agirre", crearIrakasgaienZuhaitza(new int[]{9, 6, 5}, new String[]{"EDA", "ALG", "PRO"}));
        ikasleak.put("Nahia Perez", crearIrakasgaienZuhaitza(new int[]{8, 8, 8}, new String[]{"EDA", "ALG", "PRO"}));

        // Crear la lista de ikasleak a evaluar
        LinkedList<String> ikasleLista = new LinkedList<>();
        ikasleLista.add("Jose Rodriguez");
        ikasleLista.add("Ana Agirre");
        ikasleLista.add("Nahia Perez");

        // Prueba 1: Obtener los estudiantes con la nota más alta
        System.out.println("Prueba 1: Estudiantes con la nota más alta");
        LinkedList<String> resultado1 = ikasleak.lortuNotaHoberenekoIkasleak(ikasleLista);
        System.out.println("Resultado: " + resultado1); // Esperado: ["Ana Agirre"]

        // Prueba 2: Todos tienen la misma nota más alta
        System.out.println("\nPrueba 2: Todos los estudiantes tienen la misma nota más alta");
        ikasleak.get("Jose Rodriguez").root = crearIrakasgaienZuhaitza(new int[]{9, 7, 6}, new String[]{"EDA", "ALG", "PRO"}).root;
        ikasleak.get("Nahia Perez").root = crearIrakasgaienZuhaitza(new int[]{9, 9, 9}, new String[]{"EDA", "ALG", "PRO"}).root;
        LinkedList<String> resultado2 = ikasleak.lortuNotaHoberenekoIkasleak(ikasleLista);
        System.out.println("Resultado: " + resultado2); // Esperado: ["Jose Rodriguez", "Ana Agirre", "Nahia Perez"]

        // Prueba 3: Un estudiante sin notas
        System.out.println("\nPrueba 3: Estudiante sin notas");
        ikasleak.put("Mikel Martinez", new IrakasgaienZuhaitza());
        ikasleLista.add("Mikel Martinez");
        LinkedList<String> resultado3 = ikasleak.lortuNotaHoberenekoIkasleak(ikasleLista);
        System.out.println("Resultado: " + resultado3); // Esperado: ["Jose Rodriguez", "Ana Agirre", "Nahia Perez"]
    }

    private static IrakasgaienZuhaitza crearIrakasgaienZuhaitza(int[] notak, String[] irakasgaiak) {
        IrakasgaienZuhaitza zuhaitza = new IrakasgaienZuhaitza();
        for (int i = 0; i < notak.length; i++) {
            zuhaitza.root = insertar(zuhaitza.root, new Irakasgaia(notak[i], irakasgaiak[i]));
        }
        return zuhaitza;
    }

    private static BinaryTreeNode<Irakasgaia> insertar(BinaryTreeNode<Irakasgaia> nodo, Irakasgaia irakasgaia) {
        if (nodo == null) {
            return new BinaryTreeNode<>(irakasgaia);
        }
        if (irakasgaia.izena.compareTo(nodo.content.izena) < 0) {
            nodo.left = insertar(nodo.left, irakasgaia);
        } else {
            nodo.right = insertar(nodo.right, irakasgaia);
        }
        return nodo;
    }
}
