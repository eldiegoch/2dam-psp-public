package com.psp.examen.ut5.digionantoniodiego;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ut5/examen")
public class ApiExamen {

	private static final String NOMBRE = "Di Gionantonio Rodrigo, Diego";
	List<CalificacionPregunta> listaCalificaciones = new ArrayList<CalificacionPregunta>();

	@GetMapping("mi-nombre")
	public String obtenerNombre() {
		return NOMBRE;
	}

	@GetMapping("respuesta-pregunta")
	public String obtenerRespuesta(@RequestParam Integer numero) {
		String respuesta = null;
		switch (numero) {
		case 1:
			respuesta = "DNS";
			break;
		case 2:
			respuesta = "FTP";
			break;
		case 3:
			respuesta = "SMTP";
			break;
		case 4:
			respuesta = "SSH";
			break;

		}
		return respuesta;
	}

	@PostMapping("registrar-calificacion")
	public String registrarCalificacion(@RequestBody CalificacionPregunta calificacion) {

		if (!preguntaYaExistente(calificacion)) {

			this.listaCalificaciones.add(calificacion);
			int totalPuntos = sumarPuntos();
			return String.format("El examen suma %d puntos", totalPuntos);

		} else {

			return "Esa pregunta ya ha sido incluida";
		}

	}

	/**
	 * Comprobamos si la pregunta se ha incluido ya, basándonos en el nombre
	 * 
	 * @param calificacion
	 * @return
	 */
	private boolean preguntaYaExistente(CalificacionPregunta calificacion) {
		//Hay muchas formas de hacerlo: buscar en el for usando un foreach, o un .contains 
		//pero tendriamos que poner el metodo equals en la clase y que se comparase con numero
		boolean existe = false;
		
		for (CalificacionPregunta calificacionPregunta : listaCalificaciones) {
			if(calificacionPregunta.getNumero() == calificacion.getNumero()) {
				existe = true;
				break;
			}
		}
		
		//Alternativa en funciones lambda:
		//existe = this.listaCalificaciones.stream().anyMatch(calif -> calif.getNumero() == calificacion.getNumero());
		
		return existe;
	}

	@GetMapping("resumen-calificaciones")
	public ResumenCalificaciones obtenerResumen() {
		ResumenCalificaciones resumen = new ResumenCalificaciones();
		resumen.setNombreAlumno(NOMBRE);
		resumen.setListaCalificaciones(listaCalificaciones);
		resumen.setTotalPuntuacion(sumarPuntos());

		return resumen;
	}

	/**
	 * Sumamos los puntos de las calificaciones
	 * 
	 * @return
	 */
	private int sumarPuntos() {
		int totalPuntos = 0;
		for (CalificacionPregunta calificacionPregunta : listaCalificaciones) {
			if(calificacionPregunta.isCorrecta()) {
				totalPuntos += calificacionPregunta.getPuntos();
			}
		}
		// Alternativa: utilizar funciones lambda para hacer operaciones sobre listas
		//totalPuntos = this.listaCalificaciones.stream().filter(calif -> calif.isCorrecta())
		//	.mapToInt(calif -> calif.getPuntos()).sum();
		
		return totalPuntos;
	}
	

}
