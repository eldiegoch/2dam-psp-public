package ejercicios.tcp.ejercicio4;

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
			boolean clienteAborta = false; 
			ObjectInputStream streamObjetosEntrada = new ObjectInputStream(this.socketCliente.getInputStream());
			//Cogemos el stream de salida para poner datos que lleguen al otro lado
			ObjectOutputStream streamSalida = new ObjectOutputStream(socketCliente.getOutputStream());
			
			while(!clienteAborta) {
				ResultadoCuadrado solicitud = cogerNumeroRecibido(streamObjetosEntrada);
				
				if(solicitud.getNumero() == -1) {
					clienteAborta = true;
					ponerMensajeFin(solicitud);
				}else {
					realizarCuadrado(solicitud);
				}
				
				enviarResultadoSolicitud(solicitud, streamSalida);
			}
			
			streamObjetosEntrada.close();
			streamSalida.close();
			cerrarSocket(this.socketCliente);
			
		} catch (ClassNotFoundException | IOException e) {
			mensajeHilo("Ha habido un problema al castear la clase:" + e.getStackTrace());
		} catch (InterruptedException e) {
			mensajeHilo("Ha habido un problema al  cerrar el socket:" + e.getStackTrace());
		}
		
		
	}
	
	private ResultadoCuadrado cogerNumeroRecibido(ObjectInputStream streamObjetosEntrada) throws IOException, ClassNotFoundException {
		//Cogemos el stream de entrada para poner datos que lleguen del otro lado

		ResultadoCuadrado solicitud = (ResultadoCuadrado) streamObjetosEntrada.readObject();
		return solicitud;
	}
	
	private void enviarResultadoSolicitud(ResultadoCuadrado solicitud, ObjectOutputStream streamSalida) throws IOException {
	
		mensajeHilo("Enviamos al cliente el resultado: " + solicitud.getCuadrado());
		
		streamSalida.writeObject(solicitud);
		streamSalida.flush();
	}
	private void realizarCuadrado(ResultadoCuadrado solicitud) {
		
		int resultadoCuadrado = solicitud.getNumero() * solicitud.getNumero();
		solicitud.setCuadrado(resultadoCuadrado);
	}
	private void ponerMensajeFin(ResultadoCuadrado solicitud) {
		solicitud.setMensaje("*** GRACIAS POR USAR SERVICIOS CUADRADO ***");
		solicitud.setCuadrado(-1);
	}
	
	private  void cerrarSocket(Socket socket) throws InterruptedException, IOException {
		mensajeHilo("Cerrando el  socket en 5 segundos...");
		Thread.sleep(5000);
		socket.close();

	}
	
	private void mensajeHilo(String mensaje) {
		System.out.println(String.format("HILO %d: %s", this.numHilo, mensaje));
	}
}
