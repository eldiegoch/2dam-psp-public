package ejemploRestClient;

public class Resultado {

	private boolean exito;
	private String mensaje;
	
	/**
	 * @return the exito
	 */
	public final boolean isExito() {
		return exito;
	}
	/**
	 * @param exito the exito to set
	 */
	public final void setExito(boolean exito) {
		this.exito = exito;
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
	@Override
	public String toString() {
		return "Resultado [exito=" + exito + ", mensaje=" + mensaje + "]";
	}
	
	
	
}
