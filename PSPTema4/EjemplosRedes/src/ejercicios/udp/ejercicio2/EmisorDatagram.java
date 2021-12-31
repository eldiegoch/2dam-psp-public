package ejercicios.udp.ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EmisorDatagram implements Runnable {

	private String mensajeEnviar;
	private int numeroEmisor;
	private int puertoDestino;

	public EmisorDatagram(String mensajeEnviar, int numeroEmisor, int puertoDestino) {
		super();
		this.mensajeEnviar = mensajeEnviar;
		this.numeroEmisor = numeroEmisor;
		this.puertoDestino = puertoDestino;
	}

	@Override
	public void run() {
		try {
			int portEnviar = this.puertoDestino;

			DatagramSocket datagramSocket = new DatagramSocket();
			String mensaje = this.numeroEmisor + "_" + this.mensajeEnviar;

			InetAddress addr = InetAddress.getByName("localhost");

			// ENVIAR
			Thread.sleep(1000);
			DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, addr,
					portEnviar);
			datagramSocket.send(datagrama);


			Thread.sleep(1000);

			// RECIBIR
			byte[] mensajeRecibir = new byte[100];
			DatagramPacket datagrama1 = new DatagramPacket(mensajeRecibir, 100);
			datagramSocket.receive(datagrama1);

			System.err.println("EMISOR_" +this.numeroEmisor+ ": Mensaje recibido: " + new String(mensajeRecibir));

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}