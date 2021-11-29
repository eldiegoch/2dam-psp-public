package tiqueto.model;

import java.util.concurrent.ThreadLocalRandom;

import tiqueto.EjemploTicketMaster;

public class PromotoraConciertos extends Thread {

	final WebCompraConciertos webCompra;

	public PromotoraConciertos(WebCompraConciertos webCompra) {
		super();
		this.webCompra = webCompra;
	}

	@Override
	public void run() {
		int totalEntradas = EjemploTicketMaster.TOTAL_ENTRADAS;
		int entradasCadaTanda = EjemploTicketMaster.REPOSICION_ENTRADAS;
		int contadorEntradas = 0;
		//Incrementamos en 50 
		while(contadorEntradas < totalEntradas) {
			
			mensajePromotor("Repongo " + entradasCadaTanda);
			webCompra.reponerEntradas(entradasCadaTanda);
			contadorEntradas +=entradasCadaTanda;
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		
		mensajePromotor("Ya se han terminado las entradas, qué pena");
		webCompra.cerrarVenta();
	}
	
	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajePromotor(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| Promotora: " + mensaje);
		
	}
}
