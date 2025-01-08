package MatrizeSakabanatua;

public class MatrizeA<T> {
	NodeA<T> first;
	
	public MatrizeB<T> bihurtu(MatrizeA<T> m){
		if (m.first == null) return null;
		MatrizeB<T> emaitza = new MatrizeB<>();
		NodeBE<T> elag = null;
		NodeA<T> unekoa = m.first;
		NodeBZ<T> azkenTxertatua = null;
		while (unekoa != null) {
			NodeBZ<T> berria = new NodeBZ<>(unekoa.zutabea, unekoa.balioa);
			if (emaitza.first == null) {
				NodeBE<T> e = new NodeBE<T>(unekoa.errenkada);
				emaitza.first=e;
				e.zutabeak=berria;
				elag = e;
			} else {
				if (unekoa.errenkada==elag.errenkada) {
					azkenTxertatua.next=berria;
				} else if (unekoa.errenkada>elag.errenkada) {
					NodeBE<T> e = new NodeBE<T>(unekoa.errenkada);
					e.zutabeak=berria;
					elag.next=e;
					elag=e;
				}
			}
			unekoa=unekoa.next;
			azkenTxertatua = berria;
		}
		return emaitza;
	}
	
	public static void main(String[] args) {
        // Crear una matriz de tipo MatrizeA con algunos nodos
        MatrizeA<Integer> matrizA = new MatrizeA<>();
        matrizA.first = crearNodoA(0, 1, 5, 
                         crearNodoA(0, 3, 10, 
                         crearNodoA(1, 0, 3, 
                         crearNodoA(1, 2, 8, 
                         crearNodoA(2, 1, 6, null)))));

        // Convertir matriz de tipo A a tipo B
        MatrizeB<Integer> matrizB = matrizA.bihurtu(matrizA);

        // Imprimir la matriz resultante de tipo MatrizeB
        System.out.println("Resultado de la conversión de MatrizeA a MatrizeB:");
        imprimirMatrizB(matrizB);
    }

    // Método auxiliar para crear nodos en MatrizeA
    public static NodeA<Integer> crearNodoA(int errenkada, int zutabea, int balioa, NodeA<Integer> next) {
        NodeA<Integer> nodo = new NodeA<>();
        nodo.errenkada = errenkada;
        nodo.zutabea = zutabea;
        nodo.balioa = balioa;
        nodo.next = next;
        return nodo;
    }

    // Método para imprimir una matriz tipo MatrizeB
    public static void imprimirMatrizB(MatrizeB<Integer> matrizB) {
        NodeBE<Integer> filaActual = matrizB.first;
        while (filaActual != null) {
            System.out.print("Errenkada " + filaActual.errenkada + ": ");
            NodeBZ<Integer> columnaActual = filaActual.zutabeak;
            while (columnaActual != null) {
                System.out.print("(" + columnaActual.zutabea + ", " + columnaActual.balioa + ") ");
                columnaActual = columnaActual.next;
            }
            System.out.println();
            filaActual = filaActual.next;
        }
    }
}