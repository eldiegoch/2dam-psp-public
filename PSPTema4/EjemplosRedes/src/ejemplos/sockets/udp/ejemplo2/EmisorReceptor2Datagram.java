package ejemplos.sockets.udp.ejemplo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class EmisorReceptor2Datagram {
	public static void main(String[] args) throws InterruptedException {
		try {
			int portOrigen = 5555;
			int portDestino = 5556;//si es en la misma maquina, tiene que ser distinto
			System.out.println("NODO2: *** Inicio ***"); 
			System.out.println("NODO2: Creando DatagramSocket en puerto " + portOrigen);

			InetSocketAddress addr = new InetSocketAddress("localhost", portOrigen);
			DatagramSocket datagramSocket = new DatagramSocket(addr);

			System.out.println("NODO2: Esperando mensajes...");

			byte[] mensaje = new byte[100];
			DatagramPacket datagrama1 = new DatagramPacket(mensaje, 100);
			datagramSocket.receive(datagrama1);

			System.err.println("NODO2: Mensaje recibido: " + new String(mensaje));

			System.out.println("NODO2: *** Introduzca mensaje a enviar... ***");
			InetAddress addr2 = InetAddress.getByName("localhost");
			String mensajeEnviar =null;
			Scanner teclado = new Scanner(System.in);
			mensajeEnviar = teclado.nextLine();
			
			Thread.sleep(1000);
			DatagramPacket datagrama2 = new DatagramPacket(mensajeEnviar.getBytes(), mensajeEnviar.getBytes().length, addr2, portDestino);
			datagramSocket.send(datagrama2);

			System.out.println("NODO2: Mensaje enviado");

			System.out.println("NODO2: Cerrando el socket datagrama");

			datagramSocket.close();
			teclado.close();

			System.out.println("\nNODO2: *** Terminado ***");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}