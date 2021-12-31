package ejercicios.tcp.ejercicio3;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class HiloCalculoCuadrado extends Thread {

	Socket socketCliente;
	int numHilo;

	public HiloCalculoCuadrado(Socket socketCliente, int numHilo) {
		super();
		this.socketCliente = socketCliente;
		this.numHilo = numHilo;
	}


	@Override
	public void run() {
	
		try {
			ResultadoCuadrado solicitud = cogerNumeroRecibido(this.socketCliente.getInputStream());
			
			enviarCuadrado(solicitud, this.socketCliente);
			
			cerrarSocket(this.socketCliente);
		} catch (ClassNotFoundException | IOException e) {
			mensajeHilo("Ha habido un problema al castear la clase:" + e.getStackTrace());
		} catch (InterruptedException e) {
			mensajeHilo("Ha habido un problema al  cerrar el socket:" + e.getStackTrace());
		}
		
		
	}
	
	private ResultadoCuadrado cogerNumeroRecibido(InputStream streamEntrada) throws IOException, ClassNotFoundException {
		//Cogemos el stream de entrada para poner datos que lleguen del otro lado
		ObjectInputStream streamObjetosEntrada = new ObjectInputStream(streamEntrada);

		ResultadoCuadrado solicitud = (ResultadoCuadrado) streamObjetosEntrada.readObject();
		return solicitud;
	}
	
	private void enviarCuadrado(ResultadoCuadrado solicitud, Socket socketCliente) throws IOException {
		
		//Cogemos el stream de salida para poner datos que lleguen al otro lado
		ObjectOutputStream streamSalida = new ObjectOutputStream(socketCliente.getOutputStream());
		
		int resultadoCuadrado = solicitud.getNumero() * solicitud.getNumero();
		mensajeHilo("Enviamos al cliente el resultado: " + resultadoCuadrado);
		solicitud.setCuadrado(resultadoCuadrado);
		
		streamSalida.writeObject(solicitud);
		streamSalida.flush();
		streamSalida.close();
		
	}
	
	
	private  void cerrarSocket(Socket socket) throws InterruptedException, IOException {
		mensajeHilo("Cerrando el  socket en 5 segundos....");
		Thread.sleep(5000);
		socket.close();

	}
	
	private void mensajeHilo(String mensaje) {
		System.out.println(String.format("HILO %d: %s", this.numHilo, mensaje));
	}
}
