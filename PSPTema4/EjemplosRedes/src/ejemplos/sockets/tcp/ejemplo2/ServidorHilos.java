package ejemplos.sockets.tcp.ejemplo2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorHilos {

	public static void main(String[] args) throws InterruptedException {
		try {
			int puertoEscuchaConexiones = 5555;
			System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
			ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);
			
			System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

			int numCliente = 0;
			List<HiloServidorComunicaciones> hilosComunicaciones = new ArrayList<>();
			while(true) {
				Socket newSocket = serverSocket.accept();
				++numCliente;
				System.out.println("SERVIDOR: Conexión recibida. El framework nos devuelve un nuevo socket para 'dialogar'. El puerto de este nuevo socket (h"+(numCliente)+") es: " + newSocket.getPort());

				HiloServidorComunicaciones hiloCom = new HiloServidorComunicaciones(newSocket, numCliente);
				hilosComunicaciones.add(hiloCom);
				hiloCom.start();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
