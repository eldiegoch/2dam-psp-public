package ejemplos.buzoncartero.multiple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DuenioCasa implements Runnable {
	
	private Buzon buzon;
	private String nombreRecibidor;
	private String tabuladores = "\t\t\t\t";
	private List<String> mensajesRecibidos = new ArrayList<>();

	// standard constructors

	public DuenioCasa(Buzon gestor, String nombre) {
		super();
		this.buzon = gestor;
		this.nombreRecibidor = nombre;
	}
	public void run() {
		String receivedMessage = "";
		do {
			mensajeReceiver("intento coger una carta");
			receivedMessage = buzon.receive(this);
			

			mensajeReceiver("¡he recibido una carta! Es: " + receivedMessage);
			mensajesRecibidos.add(receivedMessage);
			if(receivedMessage.equalsIgnoreCase("End"))
				mensajeReceiver("Toca terminar...");

			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}while(!receivedMessage.equalsIgnoreCase("End") && !buzon.isTerminado());
		
		mensajeReceiver("- No tengo más cartas que recibir -");
		
		imprimirMensajesRecibidos();

	}
	
	private void mensajeReceiver(String mensaje) {
		String mensajeCompleto = String.format("%d | %s Dueño casa %s: %s", 
				System.currentTimeMillis(),
				tabuladores,
				this.nombreRecibidor,
				mensaje);
		System.out.println(mensajeCompleto);
		
	}
	private void imprimirMensajesRecibidos() {

		this.mensajeReceiver(this.mensajesRecibidos.toString());
	}
	public String getNombreRecibidor() {
		return nombreRecibidor;
	}
	
	

}