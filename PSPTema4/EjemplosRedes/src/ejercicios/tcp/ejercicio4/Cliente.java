package ejercicios.tcp.ejercicio4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		try {
			System.out.println("CLIENTE: Iniciamos el programa cliente. Presiona tecla enter para empezar.");
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();

			System.out.println("CLIENTE: Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("CLIENTE: Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 6565);
			clientSocket.connect(addr);

			// Cogemos el stream de salida para poner datos que lleguen al otro lado
			ObjectOutputStream streamSalida = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream streamObjetosEntrada = new ObjectInputStream(clientSocket.getInputStream());
			Integer numero= 0;
			do {

				System.out.println(
						"CLIENTE: Pedimos al usuario un número para enviar al servidor y que se calcule el cuadrado");
				numero = teclado.nextInt();

				ResultadoCuadrado solicitud = new ResultadoCuadrado();
				solicitud.setNumero(numero);

				streamSalida.writeObject(solicitud);
				streamSalida.flush();


				ResultadoCuadrado respuesta = (ResultadoCuadrado) streamObjetosEntrada.readObject();

				System.out.println("La respuesta del servidor es: " + respuesta.getCuadrado());
				if(respuesta.getMensaje() != null) {
					System.out.println("El mensaje del servidor es: " + respuesta.getMensaje());
				}

			} while (numero != -1);

			System.out.println("CLIENTE: Nos hemos cansado. Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("CLIENTE: Terminado");

			teclado.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
