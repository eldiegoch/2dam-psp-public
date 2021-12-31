package ejercicios.udp.ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ReceptorDatagram {
	
	private static int NUM_MENSAJES_MAX = 20;
	public static void main(String[] args) throws InterruptedException {
		try {
			int portRecibir = 5555;
			System.out.println("RECEPTOR: *** Inicio ***");
			System.out.println("RECEPTOR: Creando DatagramSocket en puerto " + portRecibir);

			InetSocketAddress addr = new InetSocketAddress("localhost", portRecibir);
			DatagramSocket datagramSocket = new DatagramSocket(addr);

			System.out.println("RECEPTOR: Esperando mensajes...");

			int contadorMensajes = 0;
			while(contadorMensajes < NUM_MENSAJES_MAX) {
				
				// RECIBIMOS EL MENSAJE
				byte[] mensaje = new byte[100];
				DatagramPacket datagrama1 = new DatagramPacket(mensaje, 100);
				datagramSocket.receive(datagrama1);
				
				InetAddress direccionCliente = datagrama1.getAddress();
				int puertoCliente = datagrama1.getPort();
				
				contadorMensajes++;
				
				String mensajeRecibido = new String(mensaje);
				System.err.println("RECEPTOR: Mensaje recibido: " + mensajeRecibido);
				
				// DEVOLVEMOS EL MENSAJE POR DONDE NOS LLEGÓ
				String mensajeDevuelto = "ECHO: ".concat(mensajeRecibido);
				System.err.println("RECEPTOR: Vamos a devolver: " + mensajeDevuelto);
				DatagramPacket datagrama2 = new DatagramPacket(mensajeDevuelto.getBytes(),
						mensajeDevuelto.getBytes().length, direccionCliente, puertoCliente);
				datagramSocket.send(datagrama2);	
				
			}

			System.out.println("RECEPTOR: Cerrando el socket datagrama");

			datagramSocket.close();

			System.out.println("\nRECEPTOR: *** Terminado ***");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}