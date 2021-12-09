package ejemplos.sockets.tcp.objetos;

import java.io.Serializable;

public class MensajeChat implements Serializable{

	private String contenidoMensaje;
	private String emisor;
	private String receptor;
	private boolean contieneEmojis;
	/**
	 * @return the contenidoMensaje
	 */
	public String getContenidoMensaje() {
		return contenidoMensaje;
	}
	/**
	 * @param contenidoMensaje the contenidoMensaje to set
	 */
	public void setContenidoMensaje(String contenidoMensaje) {
		this.contenidoMensaje = contenidoMensaje;
	}
	/**
	 * @return the emisor
	 */
	public String getEmisor() {
		return emisor;
	}
	/**
	 * @param emisor the emisor to set
	 */
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	/**
	 * @return the receptor
	 */
	public String getReceptor() {
		return receptor;
	}
	/**
	 * @param receptor the receptor to set
	 */
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	/**
	 * @return the contieneEmojis
	 */
	public boolean isContieneEmojis() {
		return contieneEmojis;
	}
	/**
	 * @param contieneEmojis the contieneEmojis to set
	 */
	public void setContieneEmojis(boolean contieneEmojis) {
		this.contieneEmojis = contieneEmojis;
	}
	@Override
	public String toString() {
		return "MensajeChat [contenidoMensaje=" + contenidoMensaje + ", emisor=" + emisor + ", receptor=" + receptor
				+ ", contieneEmojis=" + contieneEmojis + "]";
	}
	
	
}
