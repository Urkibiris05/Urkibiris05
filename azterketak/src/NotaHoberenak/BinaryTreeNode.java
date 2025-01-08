package NotaHoberenak;

public class BinaryTreeNode<T> {
	protected T content;
	protected BinaryTreeNode<T> left;
	protected BinaryTreeNode<T> right;
    public BinaryTreeNode(T content) {
        this.content = content;
        this.left = null;
        this.right = null;
    }
}
