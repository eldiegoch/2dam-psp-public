package ejemplos.semaforo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class EjemploSemaforoParking {

	private static final int NUM_COCHES = 3;
	private static final int PLAZAS_PARKING = 1;
	
	public static void main(String[] args) throws InterruptedException {
		// Creo un semaforo para un parking con 1 plaza disponible
		Semaphore semaforo = new Semaphore(PLAZAS_PARKING);
		List<Coche> hilosCoches = new ArrayList<>();
		for (int numCoche = 1; numCoche <= NUM_COCHES; numCoche++) {
			Coche coche = new Coche(semaforo, numCoche);
			
			coche.setPriority(numCoche);
			coche.start();
			
			hilosCoches.add(coche);
			
		}

		//Ampliacion
		while(hilosCoches.stream().anyMatch(Coche::isAlive)) {
			System.out.println("ESCRIBE NUMERO DE COCHE A MATAR");
			Scanner teclado = new Scanner(System.in);
			int numeroCocheExpulsar =teclado.nextInt();
			
			for(Coche coche : hilosCoches) {
				if(coche.getNumeroCoche()== numeroCocheExpulsar) {
					coche.interrupt();
					coche.setExpulsado(true);
					break;
				}
			}
			
		}
		
		System.out.println("TODOS LOS COCHES HAN SIDO EXPULSADOS");

	}

}
