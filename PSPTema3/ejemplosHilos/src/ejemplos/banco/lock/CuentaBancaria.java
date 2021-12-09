package ejemplos.banco.lock;

public class CuentaBancaria {
	private int gastos = 0;
	private final Object lock1 = new Object();
	
	public int getGastos() {
		return gastos;
	}
	public int incrementarGasto() {
		//recupero cosas
		
		//hago otras historias
		
		//Defino una region critica, que es la que da problemas
		//porque modif
		synchronized (lock1) {
			gastos++;
		}
		return gastos;
	}
	
}








