package examen.ejerciciomacauto;

import java.util.Random;

public class Coche implements Runnable {
	McAuto pedidoMcAuto;
	int numeroCoche;
	boolean consiguioHamburguesa = false;
	int numeroHamburguesasCompradas = 0;

	public Coche(McAuto macAuto, int numero) {
		super();
		this.pedidoMcAuto = macAuto;
		this.numeroCoche = numero;
	}

	@Override
	public void run() {
		try {
			int aleatorio = (int) (Math.random() * 1000);
			int numOcupantesCoche = (new Random()).nextInt(5) + 1;
			Thread.sleep(aleatorio);
			mensajeCoche("El coche " + numeroCoche + " llega al McAuto  tras " + aleatorio + "ms.");

			// Nos ponemos a la cola (cogemos el semaforo)
			pedidoMcAuto.ponerseEnCola();

			if (pedidoMcAuto.realizarPedido(numeroCoche, numOcupantesCoche)) {
				this.consiguioHamburguesa = true;
				numeroHamburguesasCompradas = numOcupantesCoche;
				mensajeCoche("Me han dado el pedido, ¡A COMER!");
			} else {
				mensajeCoche("¡Vaya! No hay más hamburguesas! Nos vamos al BurriKing");
			}

			// Salimos del McAuto (salimos del semaforo)
			pedidoMcAuto.salirDelMcAuto();
			mensajeCoche("Coche " + numeroCoche + " se ha ido del McAuto");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getNumeroCoche() {
		return numeroCoche;
	}

	public void setNumeroCoche(int numeroCoche) {
		this.numeroCoche = numeroCoche;
	}

	/**
	 * @return the consiguioHamburguesa
	 */
	public boolean isConsiguioHamburguesa() {
		return consiguioHamburguesa;
	}

	/**
	 * @param consiguioHamburguesa the consiguioHamburguesa to set
	 */
	public void setConsiguioHamburguesa(boolean consiguioHamburguesa) {
		this.consiguioHamburguesa = consiguioHamburguesa;
	}

	private void mensajeCoche(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| Coche " + numeroCoche + " : " + mensaje);
	}

	public void mostrarHamburguesasCompradas() {
		this.mensajeCoche((this.consiguioHamburguesa ? "(BIEN!) " : "NO" ) + " he podido comprar  " + this.numeroHamburguesasCompradas + " hamburguesas");
	}
	/**
	 * @return the numeroHamburguesasCompradas
	 */
	public int getNumeroHamburguesasCompradas() {
		return numeroHamburguesasCompradas;
	}

	/**
	 * @param numeroHamburguesasCompradas the numeroHamburguesasCompradas to set
	 */
	public void setNumeroHamburguesasCompradas(int numeroHamburguesasCompradas) {
		this.numeroHamburguesasCompradas = numeroHamburguesasCompradas;
	}	
	
}
