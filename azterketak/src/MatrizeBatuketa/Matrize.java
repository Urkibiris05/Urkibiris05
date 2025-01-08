package MatrizeBatuketa;

public class Matrize<T> {

		Node<T> first;
		
		public Matrize<T> batura(Matrize<T> m1, Matrize<T> m2) {
		//aurre: 
		//post: emaitza m1 eta m2 matrizeen batura da
			Matrize<T> m = new Matrize<>();
			Node<T> unekoa1 = m1.first;
			Node<T> unekoa2 = m2.first;
			Node<T> lag = null;
			Node<T> berria;
			int comp;
			while (unekoa1!=null || unekoa2!=null) {
				berria = new Node<>();
				if (unekoa1.errenkada < unekoa2.errenkada || unekoa2 ==null) comp=-1;
				else if (unekoa1.errenkada > unekoa2.errenkada || unekoa1==null) comp=1;
				else {
					if (unekoa1.zutabea < unekoa2.zutabea) comp=-1; 
					else if (unekoa1.zutabea > unekoa2.zutabea) comp=1;
					else comp=0;
				}
				switch (comp) {
					case -1:
						berria.balioa=unekoa1.balioa;
						berria.errenkada=unekoa1.errenkada;
						berria.zutabea=unekoa1.zutabea;
						unekoa1=unekoa1.next;
						break;
					case 1:
						berria.balioa=unekoa2.balioa;
						berria.errenkada=unekoa2.errenkada;
						berria.zutabea=unekoa2.zutabea;
						unekoa2=unekoa2.next;
						break;
					case 0:
						berria.balioa=unekoa1.balioa+unekoa2.balioa;
						berria.errenkada=unekoa1.errenkada;
						berria.zutabea=unekoa1.zutabea;
						unekoa1=unekoa1.next;
						unekoa2=unekoa2.next;
						break;
				}
				if (m.first==null) {
					m.first=berria;
					lag=berria;
				} else {
					lag.next=berria;
					lag=berria;
				}
			}
		return m;
		}
		
		public static void main(String[] args) {
	        // Crear e inicializar la primera matriz
	        Matrize<Integer> m1 = new Matrize<>();
	        m1.first = crearNodo(3, 0, 0, crearNodo(4, 0, 1, crearNodo(5, 1, 1, null)));
	        
	        // Crear e inicializar la segunda matriz
	        Matrize<Integer> m2 = new Matrize<>();
	        m2.first = crearNodo(1, 0, 0, crearNodo(2, 0, 1, crearNodo(6, 1, 1, null)));
	        
	        // Imprimir matrices originales
	        System.out.println("Matriz m1:");
	        imprimirMatriz(m1);
	        System.out.println("Matriz m2:");
	        imprimirMatriz(m2);

	        // Sumar las matrices
	        Matrize<Integer> resultado = m1.batura(m1, m2);

	        // Imprimir resultado de la suma
	        System.out.println("Resultado de la suma (m1 + m2):");
	        imprimirMatriz(resultado);
	        
	     // Caso 1
	        Matrize<Integer> m1_case1 = new Matrize<>();
	        m1_case1.first = crearNodo(3, 0, 1, crearNodo(5, 1, 2, crearNodo(8, 3, 1, null)));
	        System.out.println("Matriz m1:");
	        imprimirMatriz(m1_case1);
	        Matrize<Integer> m2_case1 = new Matrize<>();
	        m2_case1.first = crearNodo(7, 0, 1, crearNodo(4, 2, 2, crearNodo(2, 3, 1, null)));
	        System.out.println("Matriz m2:");
	        imprimirMatriz(m2_case1);
	        System.out.println("Resultado de Caso 1:");
	        imprimirMatriz(m1_case1.batura(m1_case1, m2_case1));

	        // Caso 2
	        Matrize<Integer> m1_case2 = new Matrize<>();
	        m1_case2.first = crearNodo(2, 0, 0, crearNodo(9, 1, 1, crearNodo(1, 2, 3, null)));
	        System.out.println("Matriz m1:");
	        imprimirMatriz(m1_case2);
	        Matrize<Integer> m2_case2 = new Matrize<>();
	        m2_case2.first = crearNodo(3, 0, 0, crearNodo(7, 1, 1, crearNodo(5, 2, 2, crearNodo(6, 2, 3, null))));
	        System.out.println("Matriz m2:");
	        imprimirMatriz(m2_case2);
	        System.out.println("Resultado de Caso 2:");
	        imprimirMatriz(m1_case2.batura(m1_case2, m2_case2));

	        // Caso 3
	        Matrize<Integer> m1_case3 = new Matrize<>();
	        m1_case3.first = crearNodo(4, 0, 2, crearNodo(6, 1, 0, crearNodo(7, 2, 2, crearNodo(5, 4, 4, null))));
	        System.out.println("Matriz m1:");
	        imprimirMatriz(m1_case3);
	        Matrize<Integer> m2_case3 = new Matrize<>();
	        m2_case3.first = crearNodo(3, 0, 2, crearNodo(5, 1, 3, crearNodo(2, 2, 2, crearNodo(4, 4, 4, null))));
	        System.out.println("Matriz m2:");
	        imprimirMatriz(m2_case3);
	        System.out.println("Resultado de Caso 3:");
	        imprimirMatriz(m1_case3.batura(m1_case3, m2_case3));
	    }
		
	    // Método auxiliar para crear nodos enlazados
	    private static Node<Integer> crearNodo(Integer valor, Integer fila, Integer columna, Node<Integer> siguiente) {
	        Node<Integer> nodo = new Node<>();
	        nodo.balioa = valor;
	        nodo.errenkada = fila;
	        nodo.zutabea = columna;
	        nodo.next = siguiente;
	        return nodo;
	    }
	    
	    // Método para imprimir una matriz
	    private static void imprimirMatriz(Matrize<Integer> matriz) {
	        Node<Integer> actual = matriz.first;
	        if (actual == null) {
	            System.out.println("La matriz está vacía.");
	            return;
	        }
	        while (actual != null) {
	            System.out.println("Valor: " + actual.balioa + " en posición (" + actual.errenkada + ", " + actual.zutabea + ")");
	            actual = actual.next;
	        }
	    }
}
