package KostuarenKalkulua;

public class ZereginenZuhaitza {
	BinaryTreeNode root;
	
	public int kostua(String idZeregin) {
		BinaryTreeNode zeregina = bilatu(root, idZeregin);
		return kostua(zeregina);
	}
	
	private BinaryTreeNode bilatu(BinaryTreeNode z, String id) {
		if (z==null) return null;
		else if (z.element.id.equals(id)) return z;
		else {
			BinaryTreeNode ezk = bilatu(z.left, id);
			if (ezk!=null) return ezk;
			else return bilatu(z.right, id);
		}
	}
	
	private int kostua(BinaryTreeNode z) {
		if (z==null) return 0;
		else return kostua(z.left)+z.element.kostua+kostua(z.right);
	}
	
	 // Nuevo método para imprimir el árbol de forma visual
    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
        } else {
            printSubtree(root, 0);
        }
    }

    private void printSubtree(BinaryTreeNode node, int level) {
        if (node == null) {
            return;
        }
        printSubtree(node.right, level + 1);
        System.out.println(" ".repeat(level * 4) + node.element.id + "(" + node.element.kostua + ")");
        printSubtree(node.left, level + 1);
    }
}
