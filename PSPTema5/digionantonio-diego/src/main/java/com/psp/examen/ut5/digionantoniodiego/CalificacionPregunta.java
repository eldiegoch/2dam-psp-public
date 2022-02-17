package com.psp.examen.ut5.digionantoniodiego;

public class CalificacionPregunta implements Comparable{

	private int numero;
	private boolean correcta;
	private int puntos;
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * @return the correcta
	 */
	public boolean isCorrecta() {
		return correcta;
	}
	/**
	 * @param correcta the correcta to set
	 */
	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	@Override
	public int compareTo(Object o) {
		CalificacionPregunta p = (CalificacionPregunta)o;
		return Integer.compare(p.getNumero(), this.getNumero());
	}
	

	
}
