package ejercicios.tcp.ejercicio2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		try {
			int puertoEscuchaConexiones = 6565;
			System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
			ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);
			
			System.out.println("SERVIDOR: Realizando el bind");
			
			//Otra forma de hacer "bind" con el puerto
			System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

			Socket newSocket = serverSocket.accept();
			System.out.println("SERVIDOR: Conexión recibida. El framework nos devuelve un nuevo socket para 'dialogar'. El puerto de este nuevo socket es: " + newSocket.getPort());

			ResultadoCuadrado solicitud = cogerNumeroRecibido(newSocket.getInputStream());

			enviarCuadrado(solicitud, newSocket);
			
			cerrarSocket(newSocket);

			System.out.println("SERVIDOR: Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("SERVIDOR: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static ResultadoCuadrado cogerNumeroRecibido(InputStream streamEntrada) throws IOException, ClassNotFoundException {
		//Cogemos el stream de entrada para poner datos que lleguen del otro lado
		ObjectInputStream streamObjetosEntrada = new ObjectInputStream(streamEntrada);

		ResultadoCuadrado solicitud = (ResultadoCuadrado) streamObjetosEntrada.readObject();
		return solicitud;
	}
	
	public static void enviarCuadrado(ResultadoCuadrado solicitud, Socket socketCliente) throws IOException {
		
		//Cogemos el stream de salida para poner datos que lleguen al otro lado
		ObjectOutputStream streamSalida = new ObjectOutputStream(socketCliente.getOutputStream());
		
		int resultadoCuadrado = solicitud.getNumero() * solicitud.getNumero();
		System.out.println("SERVIDOR: Enviamos al cliente el resultado: " + resultadoCuadrado);
		solicitud.setCuadrado(resultadoCuadrado);
		
		streamSalida.writeObject(solicitud);
		streamSalida.flush();
		streamSalida.close();
		
	}
	
	private static void cerrarSocket(Socket socket) throws InterruptedException, IOException {
		System.out.println("SERVIDOR: Cerrando el nuevo socket en 5 segundos...");
		Thread.sleep(5000);
		socket.close();

	}

}
