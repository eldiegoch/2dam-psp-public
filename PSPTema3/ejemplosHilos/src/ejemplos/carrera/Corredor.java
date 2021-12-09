package ejemplos.carrera;

import java.util.Random;

public class Corredor extends Thread{

	private int dorsal;
	public Corredor(int dorsal) {
		this.dorsal = dorsal;
	}
	
	@Override
	public void run() { 
		long tiempoVuelta = 0;
		long tiempoTotal = 0;
		Random aleatorio = new Random();
		for(int vuelta = 1; vuelta <= 4; vuelta++) {
			//Se tarda como minimo 3000ms en dar una vuelta + algo aleatorio
			tiempoVuelta = aleatorio.nextInt(9)*400 + 3000;
			try {
				//Con esto "simulamos" que corre "tiempoVuelta" milisegundos
				Thread.sleep(tiempoVuelta);
			} catch (InterruptedException e) {
				System.out.println();
			}
System.out.println(
		String.format("El corredor %s con dorsal %s ha terminado la vuelta %d en %d ms",
					this.getName(), this.dorsal, vuelta, tiempoVuelta));
			//Incremento el tiempo total con el de la vuelta
			tiempoTotal += tiempoVuelta;
		}
System.out.println(
		String.format("FINISH! El corredor %s con dorsal %s ha terminado y ha tardado %d ms",
				this.getName(), this.dorsal, tiempoTotal));
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

}















