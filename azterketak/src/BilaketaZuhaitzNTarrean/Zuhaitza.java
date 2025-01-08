package BilaketaZuhaitzNTarrean;

public class Zuhaitza {
	BinaryTreeNode<Integer> root;
	
	public boolean badago(Integer elem) {
		BinaryTreeNode<Integer> z = root;
		int i = 0;
		while(z!=null) {
			if 	(z.balioak[i]==elem) return true;
			else if (elem<z.balioak[i]) {
				z = z.umeak[i];
				i=0;
			} else {
				i++;
				if (i==z.balioak.length) {
					z=z.umeak[i];
					i=0;
				}
			}
			
		}
		return false;
	}
}
