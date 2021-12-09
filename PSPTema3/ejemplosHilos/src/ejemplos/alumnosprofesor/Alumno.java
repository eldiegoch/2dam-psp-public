package ejemplos.alumnosprofesor;

public class Alumno extends Thread{
	
	Bienvenida objetoBienvenida;
	
	public Alumno(Bienvenida bienvenida){
		this.objetoBienvenida = bienvenida;
	}
	
	@Override
	public void run(){
		System.out.println("Alumno llegó.");
		try {
			Thread.sleep(1000);
			objetoBienvenida.saludarProfesor();
		} catch (InterruptedException ex) {
			System.err.println("Thread alumno interrumpido!");
			System.exit(-1);
		}
	}
}