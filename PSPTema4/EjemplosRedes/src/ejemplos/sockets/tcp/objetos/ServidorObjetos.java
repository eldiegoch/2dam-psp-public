package ejemplos.sockets.tcp.objetos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorObjetos {

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		try {
			int puertoEscuchaConexiones = 5555;
			System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
			ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);
			
			System.out.println("SERVIDOR: Realizando el bind");
			
			//Otra forma de hacer "bind" con el puerto
//			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
//			serverSocket.bind(addr);
			System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

			Socket newSocket = serverSocket.accept();
			System.out.println("SERVIDOR: Conexión recibida. El framework nos devuelve un nuevo socket para 'dialogar'. El puerto de este nuevo socket es: " + newSocket.getPort());

			//Cogemos el stream de entrada para poner datos que lleguen del otro lado
			ObjectInputStream streamEntrada = new ObjectInputStream(newSocket.getInputStream());

			MensajeChat chatRecibido = (MensajeChat) streamEntrada.readObject();

			System.out.println("SERVIDOR: Mensaje recibido: "+ chatRecibido.toString());

			System.out.println("SERVIDOR: Cerrando el nuevo socket en 5 segundos...");
			Thread.sleep(5000);
			newSocket.close();

			System.out.println("SERVIDOR: Cerrando el socket servidor");

			serverSocket.close();
			System.out.println("SERVIDOR: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
