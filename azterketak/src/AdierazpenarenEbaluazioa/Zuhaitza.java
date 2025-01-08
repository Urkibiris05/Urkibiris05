package AdierazpenarenEbaluazioa;

import java.util.HashMap;

public class Zuhaitza {
	BinaryTreeNode<InfoElemExp> root;
	
	public Integer ebaluatu(HashMap<String, Integer> tHash) {
		if (root==null) return 0;
		else return ebaluatu(tHash, root);
	}
	
	
	
	private Integer ebaluatu(HashMap<String, Integer> tHash, BinaryTreeNode<InfoElemExp> n) {
		Integer emaitzaEzk;
		Integer emaitzaEsk;
		if (n.left!=null) emaitzaEzk = ebaluatu(tHash, n.left);
		else emaitzaEzk = 0;
		if (n.right!=null) emaitzaEsk = ebaluatu(tHash, n.right);
		else emaitzaEsk = 0;
		
		if (n.element.eragigaia) {
			if (n.element.elem.equals("+")) return (emaitzaEzk+emaitzaEsk);
			else return (emaitzaEzk*emaitzaEsk);
		} else {
			return tHash.get(n.element.elem);
		}
			
	}
	
	public void print(){
		print(root);
	}
	
	private void print(BinaryTreeNode<InfoElemExp> n){
		if (n != null)
			if (!n.element.eragigaia) System.out.print(n.element.elem);
			else 
			{ System.out.print("(");
  			  print(n.left);
			  System.out.print(n.element.toString());
			  print(n.right);
			  System.out.print(")");
			}
	}
	
	public Zuhaitza(int n) { // la constructora crea árboles de ejemplo
		// Los valores son:
		//    x: 4
		//    a: 5
		//    s: 7
		//    g: 1
		//    m: 5
		//    d: 2
		if (n == 1) {
			BinaryTreeNode<InfoElemExp> n1 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("*", true));
			BinaryTreeNode<InfoElemExp> n2 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("x", false));
			BinaryTreeNode<InfoElemExp> n3 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("a", false));
			//          *  
			//         / \ 
			//         x a  
			// El resultado es: 20
			root     = n1;
			n1.left  = n2;
			n1.right = n3;
		}
		else if (n == 2) {
			BinaryTreeNode<InfoElemExp> n1 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("*", true));
			BinaryTreeNode<InfoElemExp> n2 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("+", true));
			BinaryTreeNode<InfoElemExp> n3 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("*", true));
			BinaryTreeNode<InfoElemExp> n4 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("*", true));
			BinaryTreeNode<InfoElemExp> n5 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("+", true));
			BinaryTreeNode<InfoElemExp> n6 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("+", true));
			BinaryTreeNode<InfoElemExp> n7 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("d", false));
			BinaryTreeNode<InfoElemExp> n8 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("x", false));
			BinaryTreeNode<InfoElemExp> n9 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("a", false));
			BinaryTreeNode<InfoElemExp> n10 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("s", false));
			BinaryTreeNode<InfoElemExp> n11 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("g", false));
			BinaryTreeNode<InfoElemExp> n12 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("a", false));
			BinaryTreeNode<InfoElemExp> n13 = new BinaryTreeNode<InfoElemExp>(new InfoElemExp("m", false));
	
			//                *
			//              /   \
			//             /     \
			//            /       \
			//            +        *
			//          /   \     / \
			//          *    +    +  d
			//         / \  / \  / \ 
			//         x a  s g  a m
			// Representa: ((x * a) + (s + g)) * ((a + m) * d)
			// El resultado es: 560
		
			root     = n1;
			n1.left  = n2;
			n1.right = n3;
			n2.left  = n4;
			n2.right = n5;
			n3.left  = n6;
			n3.right = n7;
			n4.left  = n8;
			n4.right = n9;
			n5.left  = n10;
			n5.right = n11;
			n6.left  = n12;
			n6.right = n13;
	}
	}
	public static void main(String[] args) {		
		HashMap<String, Integer> th = new HashMap<String, Integer>();
		// Los valores son:
		//    x: 4
		//    a: 5
		//    s: 7
		//    g: 1
		//    m: 5
		//    d: 2
		th.put("x",  4);
		th.put("a",  5);
		th.put("s",  7);
		th.put("g",  1);
		th.put("m",  5);
		th.put("d",  2);
		
		Zuhaitza a = new Zuhaitza(1);
		a.print();
		System.out.println();
		System.out.println("El resultado es: " + a.ebaluatu(th));
		
		a = new Zuhaitza(2);
		a.print();
		System.out.println();
		System.out.println("El resultado es: " + a.ebaluatu(th));
	}
	
	
}	

