package ejemplos.sockets.tcp.objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteObjetos {

	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println("CLIENTE: Iniciamos el programa cliente. Presiona tecla enter para empezar.");
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			System.out.println("CLIENTE: Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("CLIENTE: Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			//Cogemos el stream de salida para poner datos que lleguen al otro lado
			ObjectOutputStream streamSalida = new ObjectOutputStream(clientSocket.getOutputStream());
			
			System.out.println("CLIENTE: Pedimos al usuario un mensaje para enviar al servidor");
			String chat = teclado.nextLine();

			System.out.println("CLIENTE: 5 segundos de dormir, y mandamos : " + chat);
			Thread.sleep(5000);
			
			MensajeChat mensajeChat = generarMensajeChat();
			mensajeChat.setContenidoMensaje(chat);
			streamSalida.writeObject(mensajeChat);
			Thread.sleep(10000);
			System.out.println("CLIENTE: Nos hemos cansado. Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("CLIENTE: Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static MensajeChat generarMensajeChat(){
		MensajeChat nuevoMensaje = new MensajeChat();
		nuevoMensaje.setContieneEmojis(true);
		nuevoMensaje.setEmisor("Pepe");
		nuevoMensaje.setReceptor("Lola");
		return nuevoMensaje;
	}
}
