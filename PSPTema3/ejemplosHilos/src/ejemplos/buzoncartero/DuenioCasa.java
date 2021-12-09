package ejemplos.buzoncartero;

import java.util.concurrent.ThreadLocalRandom;

public class DuenioCasa implements Runnable {
	
	private Buzon buzon;
	private String tabuladores = "\t\t\t\t";

	public DuenioCasa(Buzon gestor) {
		super();
		this.buzon = gestor;
	}
	public void run() {
		String receivedMessage = "";
		do {
			mensajeReceiver("intento coger una carta");
			receivedMessage = buzon.recogerCorreo();
			
			mensajeReceiver("¡carta recibida! Es: " + receivedMessage);
			if(receivedMessage.equalsIgnoreCase("End"))
				mensajeReceiver("Toca terminar...");

			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}while(!receivedMessage.equalsIgnoreCase("End"));
		
		mensajeReceiver("- No tengo más cartas que recibir -");

	}
	
	private void mensajeReceiver(String mensaje) {
		System.out.println(System.currentTimeMillis() + "|" + tabuladores +" Dueño casa: " + mensaje);
		
	}

}