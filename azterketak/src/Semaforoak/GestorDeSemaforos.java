package Semaforoak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class GestorDeSemaforos {

	int NSEMAFOROS = 3;
	int N = 2; // número máximo de coches que pasan cuando el semáforo se pone en verde


	int[][] salidasSemaforos; // Matriz de dimensión Nx4
	// * Una fila por cada semáforo
	// * Cada semáforo tiene 4 valores que indican
	// a dónde se dirigirá un coche siguiendo las
	// 4 direcciones posibles:
	// - -1: no hay salida en esa dirección
	// - 0..N: identificador de un semáforo
	// - 999999: sale de la ciudad


	final int S0 = 0;
	final int S1 = 1;
	final int S2 = 2;
	final int SALIDA = 999999;
	final int CERRADO = -1;
	int randomValue; // simulación de aleatoriedad para la salida de un semáforo

	public void inicializarSalidasSemaforos() {
		// Post: por cada semaforo, se ha añadido la información
		// correspondiente al plano de la ciudad, es decir, se han
		// actualizado las 4 salidas correspondientes a cada una de
		// las direcciones (norte, sur, este, oeste)
		salidasSemaforos = new int[NSEMAFOROS][4];
		int[] s0 = { CERRADO, S2, S1, CERRADO };
		salidasSemaforos[S0] = s0;
		int[] s1 = { S0, S0, S0, CERRADO };
		salidasSemaforos[S1] = s1;
		int[] s2 = { CERRADO, SALIDA, S0, CERRADO };
		salidasSemaforos[S2] = s2;
	}

	public Integer aDonde(int semaforo, String matricula) {
		// Post: el resultado es un valor que indica la
		// dirección que tomará ese vehículo cuando
		// sale del semáforo:
		// * 0: Norte
		// * 1: Sur
		// * 2: Este
		// * 3: Oeste
		int NORTE = 0;
		int SUR = 1;
		int ESTE = 2;
		int OESTE = 3;
		
		int direccion;

		randomValue++;
		if (semaforo == S0)
			if (randomValue % 2 == 0)
				direccion=  SUR;
			else
				direccion = ESTE;
		else if (semaforo == S1)
			direccion = ESTE;
		else // S2
		if (randomValue % 2 == 0)
			direccion = SUR;
		else
			direccion= ESTE;

		return salidasSemaforos[semaforo][direccion];
		//return direccion;
	}

	public void inicializarCiudad(String[] matriz) {

		//           01234567890123456789012345678901234567890123456789
		matriz[0] = "                                          Entrada ";
		matriz[1] = "------------------------------------------- |     ";
		matriz[2] = "|                                           v    |";
		matriz[3] = "|            ->                   ->             |";
		matriz[4] = "|       ----------          ----------           |";
		matriz[5] = "|       |         |         |         |     |    |";
		matriz[6] = "|    ^  |         |     ^   |         |     v    |";
		matriz[7] = "|    |  |         |     |   |         |          |";
		matriz[8] = "|       |         |         |         |          |";
		matriz[9] = "|       ----------           ----------          |";
		matriz[10] = "|                                          S0    |";
		matriz[11] = "|           <-              S1    <-             |";
		matriz[12] = "|                                                |";
		matriz[13] = "|       ----------          ----------           |";
		matriz[14] = "|       |         |     |   |         |     |    |";
		matriz[15] = "|    ^  |         |     v   |         |     v    |";
		matriz[16] = "|    |  |         |         |         |          |";
		matriz[17] = "|       |         |         |         |          |";
		matriz[18] = "|       ----------           ----------          |";
		matriz[19] = "|            <-                 <-         S2    |";
		matriz[20] = "|                                           |    |";
		matriz[21] = "------------------------------------------  v     ";
		matriz[22] = "                                           Salida ";
	}

	public void escribirMapa(Queue<String>[] semaforos) {
		String[] matriz;
		Queue<String> colaAux;
		Integer indice;

		matriz = new String[23];
		inicializarCiudad(matriz);

		colaAux = new LinkedList<String>();
		Integer ind = 9; // primera posición de S0
		while (!semaforos[S0].isEmpty()) {
			String coche = semaforos[S0].remove();
			colaAux.add(coche);
			char[] fila = matriz[ind].toCharArray();
			fila[43] = coche.charAt(0);
			fila[44] = coche.charAt(1);
			matriz[ind] = String.valueOf(fila);
			// matriz[ind].(42..43) := K;
			ind--;
		}
		while (!colaAux.isEmpty()) { // volvemos a colocar los coches en el semáforo
			String coche = colaAux.remove();
			semaforos[S0].add(coche);
		}

		colaAux = new LinkedList<String>();
		ind = 18; // primera posición de S2
		while (!semaforos[S2].isEmpty()) {
			String coche = semaforos[S2].remove();
			colaAux.add(coche);
			char[] fila = matriz[ind].toCharArray();
			fila[43] = coche.charAt(0);
			fila[44] = coche.charAt(1);
			matriz[ind] = String.valueOf(fila);
			// matriz[ind].(42..43) := K;
			ind--;
		}
		while (!colaAux.isEmpty()) { // volvemos a colocar los coches en el semáforo
			String coche = colaAux.remove();
			semaforos[S2].add(coche);
		}

		colaAux = new LinkedList<String>();
		ind = 34; // primera posición de S1
		while (!semaforos[S1].isEmpty()) {
			String coche = semaforos[S1].remove();
			colaAux.add(coche);
			char[] fila = matriz[11].toCharArray();
			fila[ind] = coche.charAt(0);
			fila[ind + 1] = coche.charAt(1);
			matriz[11] = String.valueOf(fila);
			ind = ind + 3;
		}
		while (!colaAux.isEmpty()) { // volvemos a colocar los coches en el semáforo
			String coche = colaAux.remove();
			semaforos[S1].add(coche);
		}

		for (String linea : matriz)
			System.out.println(linea);
		System.out.println("================================================================");
	}

	public void simularTrafico(ArrayList<Evento> eventos) {
		// Post: se han realizado los movimientos correspondientes a
		// “eventos”
		// Se han escrito en la salida los movimientos realizados

		// para saber en qué semáforo estaba un coche que se ha averiado
		HashMap<String, Integer> cocheSemaforo = new HashMap<String, Integer>();

		// Inicializar los semáforos
		Queue<String>[] semaforos =(LinkedList<String>[]) new LinkedList[NSEMAFOROS];
		for (int i=0; i< NSEMAFOROS;i++) {
			semaforos[i] = new LinkedList<String>();
		}
		
		

		inicializarSalidasSemaforos();

		java.util.Iterator<Evento> it = eventos.iterator();

		while (it.hasNext()) {
			Evento eventoActual = it.next();
			switch (eventoActual.tipo) {
				case 'E':
					semaforos[0].add(eventoActual.valor);
					cocheSemaforo.put(eventoActual.valor, 0);
					System.out.println(eventoActual.valor+" kotxea 0 semaforora iritsi da");
					break;
				case 'V':
					Queue<String> semaforoActual = semaforos[Integer.parseInt(eventoActual.valor)];
					int kont = 0;
					while (kont < N && !semaforoActual.isEmpty()) {
						String matriculaActual = semaforoActual.remove();
						Integer nora = aDonde(Integer.parseInt(eventoActual.valor), matriculaActual);
						if (nora == 999999) {
							cocheSemaforo.remove(matriculaActual);
							System.out.println(matriculaActual+" kotxea hiritik atera da.");
						}
						else if (nora==-1) {}
						else {
							cocheSemaforo.remove(matriculaActual);
							cocheSemaforo.put(matriculaActual, nora);
							semaforos[nora].add(matriculaActual);
							System.out.println(matriculaActual+" kotxea "+nora+" semaforora iritsi da.");
						}
						kont++;
					}
					break;
				case 'A':
					Integer semaforoa = cocheSemaforo.get(eventoActual.valor);
					semaforoActual = semaforos[semaforoa];
					boolean aurkitua = false;
					for (String matriculaActual : semaforoActual) {
						if (matriculaActual.equals(eventoActual.valor)) {
							aurkitua=true;
						}
					}
					if (aurkitua) {
						semaforoActual.remove(eventoActual.valor);
						System.out.println(eventoActual.valor+" kotxea "+semaforoa+" semaforoan izorratu da.");
					}
					break;
				case 'R':
					semaforoa = cocheSemaforo.get(eventoActual.valor);
					semaforoActual=semaforos[semaforoa];
					semaforoActual.add(eventoActual.valor);
					System.out.println(eventoActual.valor+" kotxea konpondu da eta "+semaforoa+" semaforoan jarri da.");
					break;
			
			}



                       // Para visualizar el estado de los semáforos a cada paso:
			escribirMapa(semaforos);
		}

	}
	
	public ArrayList<Evento> generarPrueba() {
		ArrayList<Evento> l = new ArrayList<Evento>();
		
		Evento e;
		e = new Evento('E', "M1");
		l.add(e);
		e = new Evento('E', "M2");
		l.add(e);
		e = new Evento('E', "M3");
		l.add(e);
		e = new Evento('E', "M4");
		l.add(e);
		e = new Evento('V', "0"); // S0
		l.add(e);
		e = new Evento('V', "0"); // S0
		l.add(e);
		e = new Evento('E', "M5");
		l.add(e);
		e = new Evento('E', "M6");
		l.add(e);
		e = new Evento('V', "1"); // S1
		l.add(e);
		e = new Evento('V', "2"); // S2
		l.add(e);
		e = new Evento('V', "0"); // S0
		l.add(e);
		e = new Evento('A', "M1"); // S0
		l.add(e);
		e = new Evento('R', "M1"); // S0
		l.add(e);
		
		return l;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GestorDeSemaforos gestor = new GestorDeSemaforos();
		gestor.inicializarSalidasSemaforos();
		
		ArrayList<Evento> eventos = gestor.generarPrueba();
		
		gestor.simularTrafico(eventos);
	}

}
