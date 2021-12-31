package ejercicios.tcp.ejercicio1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws InterruptedException {
		try {
			int puertoEscuchaConexiones = 6565;
			System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
			ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);
			
			System.out.println("SERVIDOR: Realizando el bind");
			
			//Otra forma de hacer "bind" con el puerto
			System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

			Socket newSocket = serverSocket.accept();
			System.out.println("SERVIDOR: Conexión recibida. El framework nos devuelve un nuevo socket para 'dialogar'. El puerto de este nuevo socket es: " + newSocket.getPort());

			//Cogemos el stream de entrada para poner datos que lleguen del otro lado
			Integer numeroRecibido = cogerNumeroRecibido(newSocket.getInputStream());

			enviarCuadrado(numeroRecibido, newSocket);
			
			cerrarSocket(newSocket);

			System.out.println("SERVIDOR: Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("SERVIDOR: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Integer cogerNumeroRecibido(InputStream streamEntrada) throws IOException {
		byte[] mensaje = new byte[10];
		streamEntrada.read(mensaje);
		String mensajeRecibido = new String(mensaje);
		System.out.println("SERVIDOR: Mensaje recibido: "+mensajeRecibido);

		return Integer.parseInt(mensajeRecibido.trim());
	}
	
	public static void enviarCuadrado(Integer numeroRecibido, Socket socketCliente) throws IOException {
		
		OutputStreamWriter escritor = new OutputStreamWriter(socketCliente.getOutputStream());
		Integer cuadrado = Math.multiplyExact(numeroRecibido,numeroRecibido);
		escritor.write("El cuadrado de " + numeroRecibido + " es "  + cuadrado);
		escritor.flush();
		escritor.close();
		System.out.println("Enviando a cliente la respuesta");
		
	}
	
	private static void cerrarSocket(Socket socket) throws InterruptedException, IOException {
		System.out.println("SERVIDOR: Cerrando el nuevo socket en 5 segundos...");
		Thread.sleep(5000);
		socket.close();

	}

}
