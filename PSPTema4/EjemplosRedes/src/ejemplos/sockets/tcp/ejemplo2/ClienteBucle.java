package ejemplos.sockets.tcp.ejemplo2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBucle {

	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println("CLIENTE: Iniciamos el programa cliente. Presiona tecla enter para empezar.");
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			System.out.println("CLIENTE: Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("CLIENTE: Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			//Cogemos el stream de salida para poner datos que lleguen al otro lado
			OutputStream streamSalida = clientSocket.getOutputStream();
			
			System.out.println("CLIENTE: Pedimos al usuario un mensaje para enviar al servidor");
			String mensaje = null;

			do {
				mensaje = teclado.nextLine();
			System.out.println("CLIENTE: 1 segundos de dormir, y mandamos : " + mensaje);
				Thread.sleep(1000);
				//Añadimos un salto de linea para indicar fin de linea
				mensaje = mensaje.concat("\n");
				streamSalida.write(mensaje.getBytes());
				streamSalida.flush();
			}while(!mensaje.equalsIgnoreCase("END\n"));

				
			System.out.println("CLIENTE: Nos hemos cansado. Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("CLIENTE: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
