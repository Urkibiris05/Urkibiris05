package ZuhaitzGenealogikoa;

import java.util.HashMap;

public class ZuhaitzGenealogikoa {
	BinaryTreeNode<Pertsona> root;
	
	public boolean bizkaitarPetoaDa(String izena, HashMap<String, String> herriak){
		BinaryTreeNode<Pertsona> z = bilatu(izena);
		if (z==null) return false;
		else return bizkaitarPetoaDa(z, herriak);
	}
	
	private boolean bizkaitarPetoaDa(BinaryTreeNode<Pertsona> z, HashMap<String, String> herriak) {
		if (z==null) return true;
		else if (!herriak.get(z.data.jaioterria).equals("Bizkaia")) return false;
		else {
			boolean ezk = bizkaitarPetoaDa(z.left, herriak);
			if (ezk) return bizkaitarPetoaDa(z.right, herriak);
			else return false;
		}
	}
	
	public BinaryTreeNode<Pertsona> bilatu(String izena) {
		return bilatu(izena, root);
	}
	
	private BinaryTreeNode<Pertsona> bilatu(String izena, BinaryTreeNode<Pertsona> n){
		if (n==null) return null;
		else if (n.data.izena.equals(izena)) return n;
		else {
			BinaryTreeNode<Pertsona> pEzk = bilatu(izena, n.left);
			if (pEzk!=null) return pEzk;
			else return bilatu(izena, n.right);
		}
		
	}
}
