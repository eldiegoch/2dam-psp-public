package ejemplos;

public class LanzadorHiloThread {

	public static void main(String[] args) {

		System.out.println("Empieza el coordinador");
		for(int cont = 0; cont < 10; cont++) {
			
			HiloThread miHilo = new HiloThread();
			miHilo.setName("Hilo" + cont);
			miHilo.start();
		}
		System.out.println("Termina el coordinador");
	}

}
