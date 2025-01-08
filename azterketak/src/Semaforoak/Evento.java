package Semaforoak;

public class Evento {
	   char tipo;    // Posibilidades:
       //    * ‘E’ entra un coche a la ciudad
       //    * ‘V’ un semáforo se pone verde
       //    * ‘A’ un coche se avería
       //    * ‘R’ un coche ha sido reparado

	   String valor; // Si el evento es ‘E’, ‘A’, o ‘R’ será la
       //         matrícula de un coche.
       // Si el evento es ‘V’ será el número del semáforo
	   
	   public Evento(char tipo, String valor) {
		   this.tipo = tipo;
		   this.valor = valor;
	   }
}
