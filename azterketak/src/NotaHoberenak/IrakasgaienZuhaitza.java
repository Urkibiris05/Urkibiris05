package NotaHoberenak;

public class IrakasgaienZuhaitza extends BinarySearchTree<Irakasgaia> {
	public Integer notaHoberena() {
		return notaHoberena(root);
	}
	private Integer notaHoberena(BinaryTreeNode<Irakasgaia> z) {
		if (z==null) return -1;
		else {
			Integer notaEzk = notaHoberena(z.left);
			Integer notaEsk = notaHoberena(z.right);
			if (z.content.nota>=notaEzk) {
				if (z.content.nota>=notaEsk) return z.content.nota;
				else return notaEsk;
			} else {
				if (notaEzk>=notaEsk) return notaEzk;
				else return notaEsk;
			}
		}
	}
}
