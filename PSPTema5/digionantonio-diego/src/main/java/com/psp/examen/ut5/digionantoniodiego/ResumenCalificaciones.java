package com.psp.examen.ut5.digionantoniodiego;

import java.util.List;

public class ResumenCalificaciones {

	private String nombreAlumno;
	private List<CalificacionPregunta> listaCalificaciones;
	private int totalPuntuacion;
	/**
	 * @return the nombreAlumno
	 */
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	/**
	 * @param nombreAlumno the nombreAlumno to set
	 */
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	/**
	 * @return the listaCalificaciones
	 */
	public List<CalificacionPregunta> getListaCalificaciones() {
		return listaCalificaciones;
	}
	/**
	 * @param listaCalificaciones the listaCalificaciones to set
	 */
	public void setListaCalificaciones(List<CalificacionPregunta> listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}
	/**
	 * @return the totalPuntuacion
	 */
	public int getTotalPuntuacion() {
		return totalPuntuacion;
	}
	/**
	 * @param totalPuntuacion the totalPuntuacion to set
	 */
	public void setTotalPuntuacion(int totalPuntuacion) {
		this.totalPuntuacion = totalPuntuacion;
	}
	@Override
	public String toString() {
		return "ResumenCalificaciones [nombreAlumno=" + nombreAlumno + ", listaCalificaciones=" + listaCalificaciones
				+ ", totalPuntuacion=" + totalPuntuacion + "]";
	}
	
	
}
