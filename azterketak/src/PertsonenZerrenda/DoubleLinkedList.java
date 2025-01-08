package PertsonenZerrenda;

public class DoubleLinkedList {
	//Bi norabideko esteka, zerrenda ez zirkularra
	DoubleNode first;
	/*
	public void zerrendaLortu(ListaDePersonas l) {
	//aurre: 
	//post: l-ko balioekin zerrenda sortu da
	//		zerrenda estekadura bikoitzekoa da, ez zirkularra
	//		pertsona bakoitzak bere erosketen zerrenda zirkularra izango du
		first = null;
		DoubleNode azkena = null;
		
		for (String pertsona : l.pertsonak) {
			DoubleNode berria = new DoubleNode(pertsona);
			if (first==null) {
				first=berria;
				azkena=berria;
			} else {
				azkena.next=berria;
				berria.prev=azkena;
				azkena=berria;
			}
		}
		for (int i=l.pertsonak.length -1; i>=0; i--) {
			Node lag = azkena.erosketak;
			for (int j=l.erosketak[i].length -1; j>=0; j--) {
				Node erosketa = new Node(l.erosketak[i][j]);
				if (lag==null) {
					azkena.erosketak=erosketa;
					lag=erosketa;
					lag.next=lag;
				} else {
					lag.next=erosketa;
					erosketa.next=azkena.erosketak;
					lag=lag.next;
				}
			}
			azkena=azkena.prev;
		}
	}
	*/
	public void zerrendaLortu(ListaDePersonas l) {
	//aurre:
	//post: l-ko balioekin zerrenda sortu da
	//		zerrenda estekadura bikoitzekoa da, ez zirkularra
	//		pertsona bakoitzak bere erosketen zerrenda zirkularra izango du

        DoubleNode unekoa = null;
        Node lag = null;
        int i = 0;
        for (String pertsona : l.pertsonak) {
            DoubleNode berria = new DoubleNode(pertsona);
            if (first==null){
                first=berria;
                unekoa=berria;
            } else {
                berria.prev=unekoa;
                unekoa.next=berria;
                unekoa=berria;
            }
            for (String erosketa : l.erosketak[i]){
                Node berria2 = new Node(erosketa);
                if (berria.erosketak==null){
                    berria.erosketak=berria2;
                    lag=berria2;
                }else{
                    lag.next=berria2;
                    lag=lag.next;
                }
                lag.next=berria.erosketak;
            }  
            i++;
        }

	}

	
    public void print() {
        DoubleNode act = this.first;
        while (act != null) {
            System.out.print(act.data + ": ");
            Node actC = act.erosketak;
            if (actC != null) {
                while (actC.next != act.erosketak) {
                    System.out.print(actC.data + ", ");
                    actC = actC.next;
                }
                System.out.println(actC.data);
            }
            act = act.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList lista = new DoubleLinkedList();
        ListaDePersonas lp = new ListaDePersonas();
        lista.zerrendaLortu(lp);
        lista.print();

    }
}
