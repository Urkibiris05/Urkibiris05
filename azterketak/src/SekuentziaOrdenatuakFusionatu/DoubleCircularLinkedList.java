package SekuentziaOrdenatuakFusionatu;

public class DoubleCircularLinkedList{
	
	DoubleNode<String> first;

	public void sekuentziakFusionatu(String[] s){
	//aurre: s1 eta s2 sekuentziak ordenatuta daude (goranzko ordenan)
	//post: emaitza s1 eta s2ko elementuak izango dituen zerrenda zirkularra izango da.
	//		Zerrenda goranzko ordenan egongo da.
		if (s.length==0) return;
		DoubleNode<String> unekoa = first;
		int i = 0;
		String hitza;
		boolean bueltaOsoa=false;
		do {
			hitza=s[i];
			if (unekoa==null) {
				DoubleNode<String> berria = new DoubleNode<>(hitza);
				first = berria;
				unekoa = berria;
				unekoa.next=unekoa;
				unekoa.prev=unekoa; 
				i++;
			}
			else if (hitza.compareTo(unekoa.data)<=0 || bueltaOsoa) {
				DoubleNode<String> berria = new DoubleNode<>(hitza);
				berria.next=unekoa;
				berria.prev=unekoa.prev;
				unekoa.prev.next=berria;
				unekoa.prev=berria;
				if (unekoa==first && !bueltaOsoa) first=berria;
				i++;
			} else {
				unekoa=unekoa.next;
				if (unekoa==first) bueltaOsoa=true;
			}
		} while(i<s.length);
	}
	
	public class DoubleNode<T>{
		T data;
		DoubleNode<T> prev;
		DoubleNode<T> next;
		
		public DoubleNode (T pData) {
			data=pData;
			prev=null;
			next=null;
		}
	}

	public void print(){
		if (first == null){}
		else{
			System.out.println(first.data);
			DoubleNode<String> unekoa = first.next;
			while (unekoa != first){
				System.out.println(unekoa.data);
				unekoa = unekoa.next;
			}
		}
	}
	
	public static void main(String[] args) {
		String[] s1 = {"ama", "oso"};
		DoubleCircularLinkedList l = new DoubleCircularLinkedList();
		l.sekuentziakFusionatu(s1);
		System.out.println("Lehen proba: (ama, oso) eman beharko luke: ");
		l.print();
		
		String[] s2 = {"aba", "eba", "ema", "pao", "zao"};
		l.sekuentziakFusionatu(s2);
		System.out.println("Bigarren proba: (aba, ama, eba, ema, oso, pao, zao) eman beharko luke: ");
		l.print();
	}
}
