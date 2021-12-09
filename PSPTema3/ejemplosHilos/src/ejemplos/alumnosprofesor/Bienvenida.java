package ejemplos.alumnosprofesor;

public class Bienvenida {
	boolean claseComenzada;
	
	public Bienvenida(){
		this.claseComenzada = false;
	}
	// Hasta que el profesor no salude no empieza la clase,
	// por lo que los alumnos esperan con un wait
	public synchronized void saludarProfesor(){
		try {
			while (!claseComenzada){
				wait();
			}
			System.out.println("Buenos días, profesor.");
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	// Cuando el profesor saluda avisa a los alumnos con notifyall de su llegada
	public synchronized void llegadaProfesor(String nombre){
		System.out.println("Buenos días a todos. Soy el profesor " + nombre);
		this.claseComenzada = true;
		notifyAll();
	}
}
