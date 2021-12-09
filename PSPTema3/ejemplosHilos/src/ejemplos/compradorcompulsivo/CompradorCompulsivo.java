package ejemplos.compradorcompulsivo;

public class CompradorCompulsivo extends Thread {

	private Cuenta cuenta;
	String nombre;
	public CompradorCompulsivo(Cuenta cuenta, String nombre) {
		super();
		this.cuenta = cuenta;
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 4; i++) {
			this.cuenta.retirarDinero(10, nombre);
		}
	}
	
	
}
