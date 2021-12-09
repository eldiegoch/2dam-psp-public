package ejemplos.interrupciones;
public class PadrePideHilo {
	public static void main(String[] args) {
		HiloDuerme miHilo = new HiloDuerme();
		System.out.println("He creado el hilo y su estado es: " + miHilo.getState());

		//Arrancamos el hilo
		miHilo.start();
		System.out.println("Se supone que el hilo está corriendo. Su estado es: " 
				+ miHilo.getState());
		
		//Esperamos durante 2 segundos a que termine el hilo
		try {
			miHilo.join(2000);
			System.out.println("Han pasado los 2seg. Su estado es: " + miHilo.getState());
		
			//Enviamos una interrupcion al hilo hijo para que se despierte
			miHilo.interrupt();
		} catch (InterruptedException e) {
			System.out.println("Alguien me ha despertado");
		}
		System.out.println("El padre termina");
	}

}







