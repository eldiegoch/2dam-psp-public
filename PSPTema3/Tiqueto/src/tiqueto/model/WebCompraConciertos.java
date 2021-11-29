package tiqueto.model;

import tiqueto.IOperacionesWeb;

public class WebCompraConciertos implements IOperacionesWeb{

	private int numeroEntradas;
	private boolean ventaAbierta = true;
	
	public WebCompraConciertos() {
		super();
	}


	@Override
	public synchronized boolean comprarEntrada() {
		
		while(!hayEntradas() && ventaAbierta) {
			
			try {
				mensajeWeb("SOLD OUT! Esperamos a que repongan entradas");
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		
		if(ventaAbierta && hayEntradas()) {
			this.numeroEntradas--;
			mensajeWeb("Entrada comprada, quedan: " + this.numeroEntradas);
		}else if(!ventaAbierta){
			return false;
		}
		
		this.notifyAll();
		
		return true;
	}


	@Override
	public synchronized int reponerEntradas(int numeroEntradas) {
		while(hayEntradas() && ventaAbierta) {
			
			try {
				mensajeWeb("Si todavía hay entradas, me espero a reponer");
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
		}
		
		this.numeroEntradas += numeroEntradas;
		mensajeWeb("Reposición: Ahora hay " + this.numeroEntradas);
		this.notifyAll();
		
		return this.numeroEntradas;
	}


	@Override
	public synchronized void cerrarVenta() {
		this.ventaAbierta = false;
		notifyAll();
	}


	@Override
	public synchronized boolean hayEntradas() {
		return this.numeroEntradas > 0;
	}


	@Override
	public int entradasRestantes() {
		return this.numeroEntradas;
	}


	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajeWeb(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| WebCompra: " + mensaje);
		
	}

}
