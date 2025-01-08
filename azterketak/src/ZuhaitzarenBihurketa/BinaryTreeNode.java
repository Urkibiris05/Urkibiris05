package ZuhaitzarenBihurketa;

public class BinaryTreeNode<T> {

	protected T content;
	protected BinaryTreeNode<T> left;
	protected BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T t) {
		 this.content = t;
	     this.left = null;
	     this.right = null;
	}
}
