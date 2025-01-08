package ZuhaitzarenBihurketa;

import java.util.HashMap;

public class ProduktuenZuhaitza extends BinaryTree<Produktu>{
	
	public HashMap<String, Integer> zuhaitzaHTBihurtu(){
		HashMap<String, Integer> emaitza = new HashMap<String, Integer>();
		zuhaitzaHTBihurtu(emaitza, root);
		return emaitza;
	}
	
	private void zuhaitzaHTBihurtu(HashMap<String, Integer> emaitza, BinaryTreeNode<Produktu> z) {
		if (z==null) {}
		else {
			if (emaitza.containsKey(z.content.izena)) {
				emaitza.put(z.content.izena, emaitza.get(z.content.izena)+z.content.salmentak);
			} else {
				emaitza.put(z.content.izena, z.content.salmentak);
			}
			zuhaitzaHTBihurtu(emaitza, z.left);
			zuhaitzaHTBihurtu(emaitza, z.right);
		}
	}
}
