package ejemplos.sockets.tcp.ejemplo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloServidorComunicaciones extends Thread {

	Socket socketCliente;
	int numHilo;

	public HiloServidorComunicaciones(Socket socketCliente, int numHilo) {
		super();
		this.socketCliente = socketCliente;
		this.numHilo = numHilo;
	}

	@Override
	public void run() {
		InputStream streamEntrada;
		try {
			streamEntrada = socketCliente.getInputStream();
			BufferedReader lectorBufer = new BufferedReader(new InputStreamReader(streamEntrada));
			String linea = lectorBufer.readLine();
			while (linea != null && !linea.equalsIgnoreCase("END")) {
				System.out.println("Hilo" + numHilo + ":" + linea);
				linea = lectorBufer.readLine();
			}
			
			System.out.println("Hilo" + numHilo + ": cerrando comunicacion");
			streamEntrada.close();
			socketCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
