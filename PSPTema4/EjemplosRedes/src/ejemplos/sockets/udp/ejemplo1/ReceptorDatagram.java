package ejemplos.sockets.udp.ejemplo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ReceptorDatagram {
	public static void main(String[] args) throws InterruptedException {
		try {
			int portRecibir = 5555;
			System.out.println("RECEPTOR: *** Inicio ***"); 
			System.out.println("RECEPTOR: Creando DatagramSocket en puerto " + portRecibir);

			InetSocketAddress addr = new InetSocketAddress("localhost", portRecibir);
			DatagramSocket datagramSocket = new DatagramSocket(addr);

			System.out.println("RECEPTOR: Esperando mensajes...");

			byte[] mensaje = new byte[100];
			DatagramPacket datagrama1 = new DatagramPacket(mensaje, 100);
			datagramSocket.receive(datagrama1);

			System.err.println("RECEPTOR: Mensaje recibido: " + new String(mensaje));

			System.out.println("RECEPTOR: Cerrando el socket datagrama");

			datagramSocket.close();

			System.out.println("\nRECEPTOR: *** Terminado ***");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}