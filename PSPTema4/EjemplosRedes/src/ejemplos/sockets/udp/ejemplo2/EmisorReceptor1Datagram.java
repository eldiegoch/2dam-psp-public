package ejemplos.sockets.udp.ejemplo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class EmisorReceptor1Datagram {
	
	public static void main(String[] args) throws InterruptedException{
		try {
			int portDestino = 5555;
			int portOrigen = 5556;//si es en la misma maquina, tiene que ser distinto
			System.out.println("NODO1: *** Inicio ***"); 

			System.out.println("NODO1: Iniciamos el DatagramSocket NODO1 en el puerto "+ portOrigen +". Presiona tecla enter para empezar.");
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			DatagramSocket datagramSocket = new DatagramSocket(portOrigen);
			System.out.println("NODO1: *** Introduzca mensaje a enviar... ***");
			String mensaje = null;
			
			mensaje = teclado.nextLine();

			InetAddress addr = InetAddress.getByName("localhost");
			System.out.println("NODO1: Enviando datagrama UDP al puerto " + portDestino);

			Thread.sleep(1000);
			DatagramPacket datagrama =
					new DatagramPacket(mensaje.getBytes(),
							mensaje.getBytes().length,
							addr, portDestino);
			datagramSocket.send(datagrama);

			System.out.println("NODO1: Mensaje enviado");


			byte[] mensajeRecibir = new byte[100];
			DatagramPacket datagrama1 = new DatagramPacket(mensajeRecibir, 100);
			datagramSocket.receive(datagrama1);

			System.err.println("NODO1: Mensaje recibido: " + new String(mensajeRecibir));

			System.out.println("NODO1: Cerrando el socket datagrama");
			datagramSocket.close();
			teclado.close();
			System.out.println("\nNODO1: *** Terminado ***"); 

		} catch (IOException e) {
			e.printStackTrace(); }
	}
}