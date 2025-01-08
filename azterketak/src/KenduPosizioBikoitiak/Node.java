package KenduPosizioBikoitiak;

public class Node<T>{
	T data;
	Node<T> next;
	Node<T> prev;
	
	public Node(T pData) {
		data=pData;
		next=null;
		prev=null;
	}
}
