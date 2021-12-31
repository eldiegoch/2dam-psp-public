package ejercicios.udp.ejercicio2;

import java.util.Random;

public class PruebaEmisores {

	private static final int PUERTO_DESTINO = 5555;

	public static void main(String[] args) {

		int numeroMaximo = 20;
		for (int numEmisor = 1; numEmisor <= numeroMaximo; numEmisor++) {

			EmisorDatagram emisor = new EmisorDatagram(generarAleatorio(), numEmisor, PUERTO_DESTINO);
			Thread hiloEmisor = new Thread(emisor);
			hiloEmisor.start();

		}
	}

	private static String generarAleatorio() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

	}

}
