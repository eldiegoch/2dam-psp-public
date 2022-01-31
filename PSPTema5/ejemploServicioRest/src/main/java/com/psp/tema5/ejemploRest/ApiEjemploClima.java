package com.psp.tema5.ejemploRest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psp.tema5.ejemploRest.model.InfoClima;
import com.psp.tema5.ejemploRest.model.Resultado;

@RestController
@RequestMapping("api/ejemplo-clima/")
public class ApiEjemploClima {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/saludo")
	public String saludo(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}
	
	@GetMapping("/infoClima")
	public InfoClima peticionClima(@RequestParam(value = "ciudad", defaultValue = "Madrid") String ciudad) {
		InfoClima clima = new InfoClima();
		clima.setCiudad(ciudad);
		//Calculamos temperatura y humedad aleatoria para comprobar que siempre da algo distinto
		Random aleatorio = new Random();
		clima.setTemperatura(aleatorio.nextInt(30));
		clima.setHumedad(aleatorio.nextInt(15));
		return clima;
	}
	
	@PostMapping("/nueva-ciudad")
	public Resultado incluirInfoClima(@RequestBody InfoClima info) {
		Resultado infoResultado = new Resultado();
		infoResultado.setExito(true);
		infoResultado.setMensaje("Todo ok, registro grabado en BD. La ciudad recibida es: " + info.getCiudad());
		return infoResultado;
	}
}
