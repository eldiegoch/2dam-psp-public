package ejemploRestClient;

public class InfoClima {

	private String ciudad;
	private double temperatura;
	private double humedad;
	/**
	 * @return the ciudad
	 */
	public final String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public final void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the temperatura
	 */
	public final double getTemperatura() {
		return temperatura;
	}
	/**
	 * @param temperatura the temperatura to set
	 */
	public final void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	/**
	 * @return the humedad
	 */
	public final double getHumedad() {
		return humedad;
	}
	/**
	 * @param humedad the humedad to set
	 */
	public final void setHumedad(double humedad) {
		this.humedad = humedad;
	}
	@Override
	public String toString() {
		return "InfoClima [ciudad=" + ciudad + ", temperatura=" + temperatura + ", humedad=" + humedad + "]";
	}
	
	
	
}
