package ZuhaitzarenBihurketa;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Crear el árbol de productos basado en el ejemplo
        ProduktuenZuhaitza produktuenZuhaitza = new ProduktuenZuhaitza();
        produktuenZuhaitza.root = construirArbol();

        // Convertir el árbol a un HashMap
        HashMap<String, Integer> salmentak = produktuenZuhaitza.zuhaitzaHTBihurtu();

        // Imprimir el resultado
        System.out.println("HashMap con las ventas totales de los productos:");
        for (String produktua : salmentak.keySet()) {
            System.out.println(produktua + ": " + salmentak.get(produktua));
        }
    }

    private static BinaryTreeNode<Produktu> construirArbol() {
        // Crear los nodos del árbol basados en el ejemplo
        BinaryTreeNode<Produktu> aspirina1 = new BinaryTreeNode<>(new Produktu(5, "aspirina"));
        BinaryTreeNode<Produktu> auxan = new BinaryTreeNode<>(new Produktu(3, "auxan"));
        BinaryTreeNode<Produktu> dakarin = new BinaryTreeNode<>(new Produktu(6, "daktarin"));
        BinaryTreeNode<Produktu> aspirina2 = new BinaryTreeNode<>(new Produktu(4, "aspirina"));
        BinaryTreeNode<Produktu> aspirina3 = new BinaryTreeNode<>(new Produktu(7, "aspirina"));

        // Construir el árbol
        aspirina1.left = auxan;
        aspirina1.right = dakarin;
        auxan.left = aspirina2;
        dakarin.right = aspirina3;

        return aspirina1;
    }
}

