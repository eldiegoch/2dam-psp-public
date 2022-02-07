package ejemploRestClient;

import java.util.Collections;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class ApacheClientGet {

	//Creamos la URL principal
	private static final String REST_URI = "http://localhost:8080/api/ejemplo-clima";

	//El objeto principal de las peticiones web
	private static WebClient clienteWeb;

	public static void main(String[] args) {

		//Creamos el cliente con la URL principal
		clienteWeb = WebClient.create(REST_URI, Collections.singletonList(new JacksonJsonProvider()));

		//Llamamos a las distintas acciones
		getSaludo();
		
		String nombreCiudad = "Gerona";
		
		InfoClima nuevaData = generarDatosClimaCiudad(nombreCiudad);
		
		incluirCiudad(nuevaData);
		getInfoClima(nombreCiudad);
	}
	

	/**
	 * Solicita los datos de clima de una ciudad
	 * @param ciudad Nombre de la ciudad a la que solicitar los datos de clima
	 */
	private static void getInfoClima(String ciudad) {
		System.out.println();
		System.out.println("---- GET INFO CLIMA ------");
		
		clienteWeb.reset();
		
		InfoClima datosClima = clienteWeb
				.path("infoClima")    //La ruta "interna" de la peticion
				.accept(MediaType.APPLICATION_JSON_TYPE)  //El tipo de dato de recepcion de datos
				.query("ciudad", ciudad)   //Parametros
				.get(InfoClima.class);     //Accion y el tipo de clase que esperamos a la vuelta

		System.out.println(datosClima);  //Mostramos los datos recibidos
		System.out.println(clienteWeb.getResponse().getStatus());  //Podemos acceder a los datos del estado de la respuesta
		
		System.out.println("---- FIN GET INFO CLIMA ------");
		System.out.println();
	}

	private static void getSaludo() {
		System.out.println();
		System.out.println("---- SALUDO ------");
		
		clienteWeb.reset();
		String respuesta = clienteWeb.path("saludo").get(String.class);
		System.out.println(respuesta);
		System.out.println(clienteWeb.getResponse().getStatus());
		System.out.println("---- FIN SALUDO ------");
	}

	
	private static void incluirCiudad(InfoClima nuevaData) {
		System.out.println();
		System.out.println("---- POST INFO CIUDAD ------");
		
		clienteWeb.reset();
		Resultado respuesta = clienteWeb
				.path("nueva-ciudad") //La ruta "interna" de la peticion
				.type(MediaType.APPLICATION_JSON_TYPE) //El tipo de dato de envio
				.accept(MediaType.APPLICATION_JSON_TYPE)  //El tipo de dato de recepcion
				.post(nuevaData, Resultado.class);     //Accion y el tipo de clase que esperamos a la vuelta
		
		System.out.println(respuesta);
		
		System.out.println("---- FIN POST INFO CIUDAD ------");
		System.out.println();

	}
	
	/**
	 * Método de utilidad que nos permite generar datos aleatorios de clima
	 * @param nombreCiudad
	 * @return
	 */
	private static InfoClima generarDatosClimaCiudad(String nombreCiudad) {
		InfoClima nuevaData = new InfoClima();
		Random aleatorio = new Random();
		nuevaData.setCiudad(nombreCiudad);
		nuevaData.setTemperatura(aleatorio.nextInt(30));
		nuevaData.setHumedad(aleatorio.nextInt(80));
		
		return nuevaData;
	}

}
