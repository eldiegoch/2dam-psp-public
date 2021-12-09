package ejemplos.semaforo;

import java.util.concurrent.Semaphore;

public class Coche extends Thread {
	Semaphore semaforo;
	int numeroCoche;
	boolean expulsado = false;

	
	public Coche(Semaphore semaforo, int numero) {
		super();
		this.semaforo = semaforo;
		this.numeroCoche = numero;
	}

	@Override
	public void run() {
		while (true && !expulsado) {
			try {
				int aleatorio = (int) (Math.random() * 5000);
				System.out.println("El coche " + numeroCoche + " recorre la ciudad " + aleatorio + "ms.");
				
				Thread.sleep(aleatorio);
				
				System.out.println("El coche " + numeroCoche + " intenta entrar al parking.");
				
				// queuelength nos dice los hilos esperando a un semaforo
				System.out.println("Hay " + semaforo.getQueueLength() + " coches en la cola del semaforo. Plazas libres en el parking: " +semaforo.availablePermits());
				if (semaforo.getQueueLength() <= 5) {
					// Cogemos el semaforo
					semaforo.acquire();
					aleatorio = (int) (Math.random() * 5000);
					System.out.println("El coche " + numeroCoche + " entra al parking y se queda " + aleatorio + "ms. El número de plazas restantes es: " + semaforo.availablePermits());
					Thread.sleep(aleatorio);
					// Liberamos el semaforo
					semaforo.release();
					System.out.println("El coche " + numeroCoche + " sale del parking ");
				} else {
					System.out.println("El coche " + numeroCoche + " no entra porque ya hay mucha cola esperando.");
				}

			} catch (InterruptedException e) {
				System.out.println("EL COCHE " + numeroCoche + " ha sido despertado");
				semaforo.release();
			}
		}
		
		System.out.println("EL COCHE " + numeroCoche + " HA SIDO EXPULSADO. FIN DE SU VIDA");

	}
	

	public int getNumeroCoche() {
		return numeroCoche;
	}

	public void setNumeroCoche(int numeroCoche) {
		this.numeroCoche = numeroCoche;
	}

	public boolean isExpulsado() {
		return expulsado;
	}

	public void setExpulsado(boolean expulsado) {
		this.expulsado = expulsado;
	}


}
