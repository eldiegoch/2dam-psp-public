package ejemplos.carrera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CoordinadorCarrera {
	public static void main(String[] args) {

		// Crear los corredores. Vamos a ir pidiendo nombres de corredores
		// y vamos a crear los objetos pasándole el nombre y el dorsal
		List<Corredor> corredores = new ArrayList<>();
		for (int numCorredor = 1; numCorredor <= 3; numCorredor++) {
			System.out.println("Introduce el nombre del corredor " + numCorredor);
			Scanner teclado = new Scanner(System.in);
			// Leemos el nombre de teclado
			String nombreCorredor = teclado.nextLine();
			// creamos nuestro objeto Corredor pasándole en el constructor el dorsal (el
			// num)
			// y le asignamos el nombre a la propiedad "name" que hereda de Thread
			Corredor nuevoCorredor = new Corredor(numCorredor);
			nuevoCorredor.setName(nombreCorredor);

			corredores.add(nuevoCorredor);
		}

		// Recorremos toda la lista diciendoles READY, HEAD SET, GO!
		// Es decir, lanzaremos nuestros hilos (¿qué método usamos?¿eh??? estoy
		// esperando...)
		System.out.println("¡EMPIEZA LA CARRERA!");
		for (Corredor corredor : corredores) {
			corredor.start();
		}

		System.out.println("Ahora vamos a ver si llegan");
		boolean siguenCorriendo = true;
		int contador = 0;
		do {
			Corredor corredor = corredores.get(contador);
			System.out.println("Esperamos al corredor: " + corredor.getDorsal());
			try {
				corredor.join(1000);
				if(corredor.isAlive()) {
					System.out.println("Seguimos esperando, vamos al siguiente");
				}else {
					System.out.println("El " + corredor.getDorsal() + " ha terminado, "
							+ "aqui está tu aplauso");
				}
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
			contador++;
			if(contador >= corredores.size()) {
				contador = 0;
			}
			
			siguenCorriendo = corredores.stream().anyMatch(Corredor::isAlive);
					
			
					
					
		}while(siguenCorriendo);
		

	}

}
