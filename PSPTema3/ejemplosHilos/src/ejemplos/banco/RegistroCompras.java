package ejemplos.banco;

public class RegistroCompras {
	
	private int numeroCompras = 0;

	public int getNumeroCompras() {
		return numeroCompras;
	}

	public int registrarCompra() {
		numeroCompras++;
		return numeroCompras;
	}

}
