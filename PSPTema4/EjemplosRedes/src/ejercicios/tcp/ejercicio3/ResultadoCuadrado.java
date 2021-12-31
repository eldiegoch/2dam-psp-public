package ejercicios.tcp.ejercicio3;

import java.io.Serializable;

public class ResultadoCuadrado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numero;
	private Integer cuadrado;
	private String mensaje;
	
	/**
	 * @return the numero
	 */
	public final Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public final void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * @return the cuadrado
	 */
	public final Integer getCuadrado() {
		return cuadrado;
	}
	/**
	 * @param cuadrado the cuadrado to set
	 */
	public final void setCuadrado(Integer cuadrado) {
		this.cuadrado = cuadrado;
	}
	/**
	 * @return the mensaje
	 */
	public final String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public final void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
