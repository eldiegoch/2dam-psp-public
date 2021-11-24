package ejemplos.redes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

/**
 * Ejemplo extraido del libro "Programacion de Servicios y Procesos". Editorial Garceta.
 * @author garceta
 *
 */
public class TestInetAddress {

	public static void main(String[] args) {

		InetAddress dir = null;
		System.out.println("==============================");
		System.out.println("SALIDA PARA LOCALHOST");
		try {
			//Obtenemos por nombre "localhost"
			dir = InetAddress.getByName("localhost");
			
			pruebaMetodos(dir);
			
			//URL www.google.es
			System.out.println("==============================");
			System.out.println("SALIDA PARA UNA URL");
			dir = InetAddress.getByName("amazon.com");
			pruebaMetodos(dir);
			
			System.out.println("==============================");

			//Array de tipo InetAddress con todas las direcciones IP asignadas a google.es
			InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
			System.out.println("De "+ dir.getHostName() +" ha sacado " + direcciones.length + " direcciones");
			for (InetAddress inetAddress : direcciones) {
				System.out.println("\t\t" + inetAddress.toString());
			}
			
			
		}catch(UnknownHostException uhe) {
			uhe.printStackTrace();
		}
	}
	
	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName(); " + dir);
		InetAddress dir2;
		try {
			//Esto sólo sirve para sacar el localhost y compararlo con lo que nos da la InetAddress por parametros
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		}catch(UnknownHostException uhe) {
			uhe.printStackTrace();
		}
		
		//Usamos metodos de la clase
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}

}
