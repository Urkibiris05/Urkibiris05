package PertsonenZerrenda;

public class DoubleNode {
	String data;
	DoubleNode prev;
	DoubleNode next;
	Node erosketak; //zerrenda zirkularra
	
	public DoubleNode(String pData) {
		data=pData;
		prev=null;
		next=null;
		erosketak=null;
	}
}
