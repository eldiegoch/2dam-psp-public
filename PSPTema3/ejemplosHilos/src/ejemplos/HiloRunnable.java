package ejemplos;

public class HiloRunnable implements Runnable{

	private final String nombre;
	public HiloRunnable(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		System.out.println("Soy el hilo y empiezo: " + nombre);
		System.out.println("Soy el hilo y termino: " + nombre);
		System.out.println();
	}
}
