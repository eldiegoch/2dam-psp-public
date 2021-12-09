package examen.ejerciciomacauto;

import java.util.concurrent.Semaphore;

public class McAuto {

	private Semaphore semaforo;
	private int hamburguesas;
	
	
	public McAuto(int numeroHamburguesas, int numCajas) {
		this.semaforo = new Semaphore(numCajas);
		hamburguesas = numeroHamburguesas;
	}
	
	public void ponerseEnCola() throws InterruptedException {
		mensajeMcAuto("Hay " + this.cochesEnCola() + " coches en la cola del McAuto.");
		this.semaforo.acquire();
	}
	public void salirDelMcAuto() throws InterruptedException {
		this.semaforo.release();
	}
	
	public int cochesEnCola() {
		return this.semaforo.getQueueLength();
	}
	
	public boolean realizarPedido(int numCoche, int numHamburguesasComprar) throws InterruptedException {
		mensajeMcAuto("Bienvenido a McAuto, el coche " + numCoche + " quiere comprar "+ numHamburguesasComprar +" hamburguesas, y quedan " + hamburguesas);
		if((hamburguesas - numHamburguesasComprar) >= 0) {
			mensajeMcAuto("Se realiza pedido de "+ numHamburguesasComprar + " hamburguesas para el coche " + numCoche + ". El pedido tarda 2 seg en darse al cliente");			
			Thread.sleep(2000);
			hamburguesas-= numHamburguesasComprar;
			mensajeMcAuto("Quedan " + hamburguesas);
			return true;
		}else {
			mensajeMcAuto("Disculpe, no tenemos tantas hamburguesas"); 
			return false;
		}
	}

	/**
	 * @return the hamburguesas
	 */
	public int getHamburguesas() {
		return hamburguesas;
	}


	private void mensajeMcAuto(String mensaje) {
		String tabuladores = "\t\t\t\t\t\t\t";
		System.out.println(System.currentTimeMillis() + "| " + tabuladores + " MacAuto : " +mensaje);
	}	
	
	
}
