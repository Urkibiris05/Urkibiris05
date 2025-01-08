package BZBngehitu;

public class BZB {
	BinaryTreeNode<Integer> root;
	
	public void add(int v) {
		BinaryTreeNode<Integer> z = root;
		BinaryTreeNode<Integer> jatorrizkoa = null;
		BinaryTreeNode<Integer> berria = new BinaryTreeNode<Integer>();
		berria.data=v;
		berria.numberOfNodes=1;
		berria.right=null;
		berria.left=null;
		int norabidea=-1;
		while (z!=null) {
			z.numberOfNodes++;
			jatorrizkoa=z;
			if (z.data<v) {
				z=z.right;
				norabidea=1;	
			} else {
				z=z.left;
				norabidea=0;
			}
		}
		if (jatorrizkoa==null) root=berria;
		else {
			if (norabidea==1) jatorrizkoa.right=berria;
			else jatorrizkoa.left=berria;
		}
	}
	
	public void printTartea(int a, int b) {
		printTartea(a, b, root);
	}
	
	private void printTartea(int a, int b, BinaryTreeNode<Integer> z) {
		if (z==null) {}
		else if (z.data>=a && z.data<=b) {
			printTartea(a, b, z.left);
			System.out.print(" "+z.data);
			printTartea(a, b, z.right);
		} else if (z.data<=b) {
			printTartea(a, b, z.right);
		} else if (z.data>=a){
			printTartea(a, b, z.left);
		}
	}
	
	public void inOrderTraversal() {
        System.out.print("Recorrido inorden: ");
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(BinaryTreeNode<Integer> z) {
        if (z == null) {
            return;
        }
        inOrderTraversal(z.left);
        System.out.print(z.data + "(" + z.numberOfNodes + ") ");
        inOrderTraversal(z.right);
    }
}
	
