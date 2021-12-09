package ejemplos.buzoncartero.multiple;


public class Buzon {
	private String contenidoBuzon;

	// True si el Dueño de la casa deberia esperar
	// False si el Cartero deberia esperar
	private boolean cartaRecogida = true;
	
	private boolean envioTerminado = false;

	public synchronized void send(String cartaAPoner) {
		while (!cartaRecogida) {
			try {
				mensajeBuzon("No han cogido la carta anterior, esperamos.... Transfer: " + cartaRecogida);
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		cartaRecogida = false;
		mensajeBuzon("(Se deja en la puerta la carta " + cartaAPoner + ")");
		
		this.contenidoBuzon = cartaAPoner;
		notifyAll();
	}

	public synchronized String receive(DuenioCasa recibidor) {
		while (cartaRecogida && !envioTerminado) {
			try {
				mensajeBuzon(recibidor.getNombreRecibidor() + ": Auch! No hay cartas para mí, esperamos... Transfer: " + cartaRecogida);
				//Esperamos 5seg
				wait(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		cartaRecogida = true;

		notifyAll();
		return contenidoBuzon;
	}
	
	private void mensajeBuzon(String mensaje) {
		System.out.println(System.currentTimeMillis() + "|  Buzon: " + mensaje);
		
	}
	
	public synchronized void envioTerminado() {
		this.envioTerminado = true;
		contenidoBuzon = "(se terminó todo)";
		notifyAll();
	}
	
	public synchronized boolean isTerminado() {
		return this.envioTerminado;
	}
	
	
}