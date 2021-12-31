package ejercicios.tcp.ejercicio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private static int MAX_PETICIONES = 3;

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		try {
			int puertoEscuchaConexiones = 6565;
			System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
			ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);
			
			System.out.println("SERVIDOR: Realizando el bind");
			
			//Otra forma de hacer "bind" con el puerto
			System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

			int contadorPeticiones = 0;
			
			while(contadorPeticiones < MAX_PETICIONES) {
				Socket newSocket = serverSocket.accept();
				System.out.println("SERVIDOR: Conexión recibida. El framework nos devuelve un nuevo socket para 'dialogar'. El puerto de este nuevo socket es: " + newSocket.getPort());

				contadorPeticiones++;
				HiloCalculoCuadrado hiloCom = new HiloCalculoCuadrado(newSocket, contadorPeticiones);
				hiloCom.start();
				System.out.println("Quedan " + (MAX_PETICIONES - contadorPeticiones) + " peticiones para terminar el server");
			}
			
			System.out.println("SERVIDOR: Nos hemos cansado de abrir conexiones. Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("SERVIDOR: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Obtiene los milisegundos que serían después de numMinutosDespues. 
	 * Se calcula el instante actual en milisegundos, y se le suma 2 minutos en esa misma unidad.
	 * 
	 * @param numMinutosDespues Parametro para indicar cuántos minutos después se quiere los milisegundos, a partir del actual
	 * @return El resultado de sumar 2 minutos a los milisegundos actuales
	 */
	public static Long obtenerInstanteFinal(int numMinutosDespues) {
		Long millisFinal = 0l;
		
		Long inicio = System.currentTimeMillis();
		millisFinal = inicio + 2 * 60 * 1000;
		return millisFinal;
	}
	
	

}
