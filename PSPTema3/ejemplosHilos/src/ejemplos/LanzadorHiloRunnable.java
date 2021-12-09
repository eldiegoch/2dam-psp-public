package ejemplos;

public class LanzadorHiloRunnable {

	public static void main(String[] args) {

		Thread hilo1 = new Thread(new HiloRunnable("H1"));
		Thread hilo2 = new Thread(new HiloRunnable("H2"));
		hilo1.start();
		hilo2.start();
		
		System.out.println("Coordinador terminado.");		
	}

}
