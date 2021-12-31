package ejercicios.tcp.ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println("CLIENTE: Iniciamos el programa cliente. Presiona tecla enter para empezar.");
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			System.out.println("CLIENTE: Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("CLIENTE: Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 6565);
			clientSocket.connect(addr);

			//Cogemos el stream de salida para poner datos que lleguen al otro lado
			OutputStream streamSalida = clientSocket.getOutputStream();
			
			System.out.println("CLIENTE: Pedimos al usuario un número para enviar al servidor y que se calcule el cuadrado");
			Integer numero = teclado.nextInt();

			String mensaje = String.valueOf(numero);
			System.out.println("CLIENTE: 1 segundos de dormir, y mandamos : " + mensaje);
			Thread.sleep(1000);
			streamSalida.write(mensaje.getBytes());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.print("El servidor nos contesta:\n ");
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}			
			System.out.println("CLIENTE: Nos hemos cansado. Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("CLIENTE: Terminado");
			teclado.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
