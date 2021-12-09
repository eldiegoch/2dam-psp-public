package ejemplos.banco;

public class CuentaBancaria {
	
	private int gastos = 0;

	public int getGastos() {
		return gastos;
	}

	public int incrementarGasto() {
		gastos++;
		return gastos;
	}

}
