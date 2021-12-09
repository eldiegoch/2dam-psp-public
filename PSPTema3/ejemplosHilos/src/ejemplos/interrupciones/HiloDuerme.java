package ejemplos.interrupciones;

public class HiloDuerme extends Thread {

	@Override
	public void run() {
		//Saludamos
		System.out.println("\t Buenas! Soy el hijo que has pedido...Me duermo. Mi estado es: " 
								+ this.getState());
		//Dormimos y manejamos la excepción relacionada con la interrupción (de sueño)
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("\t Me han despertado de mi letargo. Mi estado es: " 
						+ this.getState());
		}
		//Nos despedimos
		System.out.println("\t Más te vale tratarme bien, padre. Adiós");
	}
}








