package ejemplos.alumnosprofesor;

public class ComienzoClase {
	public static void main(String[] args) {
		
		// Objeto compartido
		Bienvenida objetoBienvenida = new Bienvenida();
		int numAlumnos = 10;
		for (int alumno = 0; alumno < numAlumnos; alumno++) {
			new Alumno(objetoBienvenida).start();
		}

		Profesor profesor = new Profesor("Patricio Moreno", objetoBienvenida);
		profesor.start();
	}
}