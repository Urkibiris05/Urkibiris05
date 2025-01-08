package Eficiencia;

public class BinaryTreeNode<T> {
	protected T content;
	protected BinaryTreeNode<T> left;
	protected BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T t){
		this.content=t;
		left=null;
		right=null;
	}
}
