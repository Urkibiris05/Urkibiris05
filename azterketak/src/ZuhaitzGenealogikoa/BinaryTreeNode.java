package ZuhaitzGenealogikoa;

public class BinaryTreeNode<T> {
	T data;
	BinaryTreeNode<T> left, right;
	
	public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
