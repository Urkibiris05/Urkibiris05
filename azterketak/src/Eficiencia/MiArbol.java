package Eficiencia;

import java.util.LinkedList;

public class MiArbol extends BinarySearchTree<String> {
	
	public double numMedioDeElementos(LinkedList<String> l){
		double numElem = 0.0;
		for (String elem: l) {
			numElem += numElem(elem, root);
		}
		return numElem/l.size();
	}
	
	private double numElem(String elem, BinaryTreeNode<String> z) {
		if (z==null) return 0;
		else if (z.content.compareTo(elem)==0) return 1.0;
		else {
			if (z.content.compareTo(elem)>0) return 1.0 + numElem(elem, z.right);
			else return 1.0 + numElem(elem, z.left);
		}
	}
}
