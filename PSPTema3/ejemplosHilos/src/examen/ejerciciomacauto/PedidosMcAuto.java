package examen.ejerciciomacauto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class PedidosMcAuto {

	private static final int NUM_COCHES = 10;
	private static final int NUM_HAMBURGUESAS = 15;
	private static final int NUM_CAJAS = 1;

	public static void main(String[] args) throws InterruptedException {
		// Creamos un McAuto con 5 hamburguesas y 1 caja
		McAuto puestoMcAuto = new McAuto(NUM_HAMBURGUESAS, NUM_CAJAS);
		
		List<Thread> hilosCoches = new ArrayList<>();
		List<Coche> coches = new ArrayList<>();
		for (int numCoche = 1; numCoche <= NUM_COCHES; numCoche++) {
			Coche coche = new Coche(puestoMcAuto, numCoche);
			Thread hiloCoche = new Thread(coche);
			hiloCoche.start();
			hilosCoches.add(hiloCoche);
			
			coches.add(coche);
		}

		for (Thread hiloCoche : hilosCoches) {
			hiloCoche.join();
		}
		System.out.println("(todos los coches se han ido)");
		System.out.println("\n Había un total de " +NUM_HAMBURGUESAS + " hamburguesas al empezar la noche");
		
		int hamburguesasCompradas = 0;
		for (Coche coche : coches) {
			coche.mostrarHamburguesasCompradas();
			hamburguesasCompradas += coche.getNumeroHamburguesasCompradas();
		}
		
		System.out.println("\n Se han comprado: " + hamburguesasCompradas);
		
	}

}
