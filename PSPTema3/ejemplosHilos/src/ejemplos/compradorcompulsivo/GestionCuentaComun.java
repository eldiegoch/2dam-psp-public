package ejemplos.compradorcompulsivo;

public class GestionCuentaComun {

	public static void main(String[] args) {

		Cuenta miCuentaIng = new Cuenta(40);
		
		CompradorCompulsivo diego = new CompradorCompulsivo(miCuentaIng, "Diego");
		CompradorCompulsivo juan = new CompradorCompulsivo(miCuentaIng, "Juan");
		diego.start();
		juan.start();
	}

}
