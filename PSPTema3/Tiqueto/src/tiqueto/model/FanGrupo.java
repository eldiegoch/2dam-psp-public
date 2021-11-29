package tiqueto.model;

import java.util.concurrent.ThreadLocalRandom;

import tiqueto.EjemploTicketMaster;

public class FanGrupo extends Thread {

	final WebCompraConciertos webCompra;
	int numeroFan;
	private String tabuladores = "\t\t\t\t";
	int entradasCompradas = 0;

	public FanGrupo(WebCompraConciertos web, int numeroFan) {
		super();
		this.numeroFan = numeroFan;
		this.webCompra = web;
	}
	
	@Override
	public void run() {
		
		int totalEntradasPorPersona = EjemploTicketMaster.MAX_ENTRADAS_POR_FAN;
		
		while(entradasCompradas < totalEntradasPorPersona) {
			
			mensajeFan("Intento comprar entrada");
			boolean exito = this.webCompra.comprarEntrada();
			if(exito) {
				entradasCompradas++;
				mensajeFan("Compré! Llevo " + entradasCompradas);
			}else {
				mensajeFan("Mierda! No he podido conseguir!");
				break;
			}
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		
	}
	
	public void dimeEntradasCompradas() {
		mensajeFan("Sólo he conseguido: " + entradasCompradas);
	}
	
	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajeFan(String mensaje) {
		System.out.println(System.currentTimeMillis() + "|" + tabuladores +" Fan "+this.numeroFan +": " + mensaje);
	}
}
