package ejemplos.buzoncartero;


public class Buzon {
	private String contenidoBuzon;

	private boolean cartaRecogida = true;

	public synchronized void ponerCarta(String cartaAPoner) {
		while (!cartaRecogida) {
			try {
				mensajeBuzon("No han cogido la carta anterior, esperamos.... Transfer: " + cartaRecogida);
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		this.contenidoBuzon = cartaAPoner;
		cartaRecogida = false;
		mensajeBuzon("(Se deja en la puerta la carta " + cartaAPoner + ")");
		
		notifyAll();
	}

	public synchronized String recogerCorreo() {
		while (cartaRecogida) {
			try {
				mensajeBuzon("Auch! No hay cartas, esperamos... Transfer: " + cartaRecogida);
				wait();
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
}