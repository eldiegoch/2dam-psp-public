package com.psp.tema5.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Ejemplo de cliente FTP adaptado de codeandcoke
 * (https://github.com/codeandcoke/java-red/blob/master/FTP/src/org/sfsoft/ftp/Ftp.java)
 *
 */
public class FtpRefactorizado {

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
			System.out.println("Conectando e iniciando sesión . . .");
			clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);

			mostrarRespuestaFtp(clienteFtp);

			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);

			// Lista directorios del FTP
			listarDirectorios(clienteFtp);

			System.out.println("Cambiamos de ruta a la /pub/example ");
			
			System.out.println("Directorio actual:  " + clienteFtp.printWorkingDirectory());
			clienteFtp.changeWorkingDirectory("/pub/example");
			System.out.println("Directorio actual:  " + clienteFtp.printWorkingDirectory());

			listarFicheros(clienteFtp);
			mostrarRespuestaFtp(clienteFtp);

			// Elige un fichero que descargar
			String ficheroRemoto = "/readme.txt";
			System.out.println("Descargando fichero '" + ficheroRemoto + "' del servidor . . .");
			// Descarga un fichero del servidor FTP
			descargarFichero(clienteFtp, ficheroRemoto);


		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			/*
			 * Cierra la sesión y desconecta del servidor FTP
			 */
			if (clienteFtp != null)
				try {
					System.out.println("Cerrando conexión y desconectando del servidor . . .");
					if (clienteFtp.isConnected()) {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
	}

	private static void listarDirectorios(FTPClient clienteFtp) throws IOException {
		System.out.println("Listando directorios del servidor . . ");
		FTPFile[] directorios = clienteFtp.listDirectories();
		for (int i = 0; i < directorios.length; i++) {
			System.out.println(" - " + directorios[i].getName());
		}
	}

	private static void listarFicheros(FTPClient clienteFtp) throws IOException {
		System.out.println("Listando el listado de archivos . . ");
		FTPFile[] ficheros = clienteFtp.listFiles();
		for (int i = 0; i < ficheros.length; i++) {
			System.out.println(" - " + ficheros[i].getName());
		}
	}

	private static void mostrarRespuestaFtp(FTPClient clienteFtp) {
		// Respuesta del servidor FTP
		System.out.println(clienteFtp.getReplyString());
		System.out.println(clienteFtp.getReplyCode());
	}
	
	private static void descargarFichero(FTPClient clienteFtp, String ficheroRemoto)
			throws FileNotFoundException, IOException {
		File ficheroLocal = new File("readme-ftp2.txt");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(ficheroLocal));
		if (clienteFtp.retrieveFile(ficheroRemoto, os))
			System.out.println("El fichero se ha recibido correctamente");

		mostrarRespuestaFtp(clienteFtp);
		os.close();
	}

}
