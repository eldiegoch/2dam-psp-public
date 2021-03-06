package ejercicios.tcp.ejercicio3;

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
			System.out.println("CLIENTE: Estableciendo la conexi?n");
			InetSocketAddress addr = new InetSocketAddress("localhost", 6565);
			clientSocket.connect(addr);

			//Cogemos el stream de salida para poner datos que lleguen al otro lado
			ObjectOutputStream streamSalida = new ObjectOutputStream(clientSocket.getOutputStream());
			
			System.out.println("CLIENTE: Pedimos al usuario un n?mero para enviar al servidor y que se calcule el cuadrado");
			Integer numero = teclado.nextInt();

			ResultadoCuadrado solicitud =new ResultadoCuadrado();
			solicitud.setNumero(numero);
			
			streamSalida.writeObject(solicitud);
			
			ObjectInputStream streamObjetosEntrada = new ObjectInputStream(clientSocket.getInputStream());

			ResultadoCuadrado respuesta = (ResultadoCuadrado) streamObjetosEntrada.readObject();
			
			System.out.println("La respuesta del servidor es: " + respuesta.getCuadrado());
			System.out.println("CLIENTE: Nos hemos cansado. Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("CLIENTE: Terminado");
			
			teclado.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
