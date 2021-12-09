package ejemplos;

public class HiloThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("\t Soy el hilo creado: " + this.getName());
		
		System.out.println("\t Soy el hilo terminando" + this.getName());
	}
}
