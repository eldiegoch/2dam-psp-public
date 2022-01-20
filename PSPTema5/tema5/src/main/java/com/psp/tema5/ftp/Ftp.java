package com.psp.tema5.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ejemplo de cliente FTP adaptado de codeandcoke
 * (https://github.com/codeandcoke/java-red/blob/master/FTP/src/org/sfsoft/ftp/Ftp.java)
 *
 */
public class Ftp {
	
	private static Logger log = LoggerFactory.getLogger(Ftp.class);

	// Tambien podemos conectar a ftp.rediris.es de forma anonima (sin especificar
	// usuario y pass)
	public static final String IP = "test.rebex.net";
	public static final int PUERTO = 21;
	public static final String USUARIO = "demo";
	public static final String CONTRASENA = "password";

	public static void main(String args[]) {

		FTPClient clienteFtp = null;

		try {
			// Conecta con el servidor FTP e inicia sesión
			log.info("Conectando e iniciando sesión . . .");
			clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);

			mostrarRespuestaFtp(clienteFtp);

			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);

			// Lista directorios del FTP
			log.info("Listando directorios del servidor . . ");
			FTPFile[] directorios = clienteFtp.listDirectories();
			for (int i = 0; i < directorios.length; i++) {
				log.info(" - " + directorios[i].getName());
			}

			log.info("Cambiamos de ruta a la /pub/example ");
			
			log.info("Directorio actual:  " + clienteFtp.printWorkingDirectory());
			clienteFtp.changeWorkingDirectory("/pub/example");
			log.info("Directorio actual:  " + clienteFtp.printWorkingDirectory());

			
			log.info("Listando el listado de archivos . . ");
			FTPFile[] ficheros = clienteFtp.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				log.info(" - " + ficheros[i].getName());
			}
			
			mostrarRespuestaFtp(clienteFtp);

			// Elige un fichero que descargar
			String ficheroRemoto = "/readme.txt";
			File ficheroLocal = new File("readme-ftp2.txt");

			log.info("Descargando fichero '" + ficheroRemoto + "' del servidor . . .");
			// Descarga un fichero del servidor FTP
			OutputStream os = new BufferedOutputStream(new FileOutputStream(ficheroLocal));
			if (clienteFtp.retrieveFile(ficheroRemoto, os))
				log.info("El fichero se ha recibido correctamente");
			os.close();
			
			mostrarRespuestaFtp(clienteFtp);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			/*
			 * Cierra la sesión y desconecta del servidor FTP
			 */
			if (clienteFtp != null)
				try {
					log.info("Cerrando conexión y desconectando del servidor . . .");
					if (clienteFtp.isConnected()) {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
	}

	private static void mostrarRespuestaFtp(FTPClient clienteFtp) {
		// Respuesta del servidor FTP
		log.debug("Respuesta: " + clienteFtp.getReplyString());
		log.debug("CodRespuesta: " + clienteFtp.getReplyCode());
	}
}
