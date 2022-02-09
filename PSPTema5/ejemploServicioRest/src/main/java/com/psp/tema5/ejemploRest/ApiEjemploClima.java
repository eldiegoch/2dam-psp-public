package com.psp.tema5.ejemploRest;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

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

	//Se añade un mapa para "simular" una BD
	private static ConcurrentHashMap<String, InfoClima> datosInfoClimaPorCiudad = new ConcurrentHashMap<>();

	@GetMapping("/saludo")
	public String saludo(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}
	
	@GetMapping("/infoClima")
	public InfoClima peticionClima(@RequestParam(value = "ciudad", defaultValue = "Madrid") String ciudad) {
		InfoClima clima = datosInfoClimaPorCiudad.get(ciudad);
		return clima;
	}
	
	@GetMapping("/todas-ciudades")
	public Collection<InfoClima> peticionClima() {
		return datosInfoClimaPorCiudad.values();
	}
	
	
	
	@PostMapping("/nueva-ciudad")
	public Resultado incluirInfoClima(@RequestBody InfoClima info) {
		Resultado infoResultado = new Resultado();
		infoResultado.setExito(true);
		infoResultado.setMensaje("Todo ok, registro grabado en BD. La ciudad recibida es: " + info.getCiudad());
		
		datosInfoClimaPorCiudad.put(info.getCiudad(), info);
		return infoResultado;
	}
	
	
}
