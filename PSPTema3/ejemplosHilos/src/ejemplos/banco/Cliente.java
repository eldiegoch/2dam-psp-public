package ejemplos.banco;

public class Cliente extends Thread{
	private int miParte = 0;
	private int gastosRealizados = 0;
	private CuentaBancaria cuenta;
	
	@Override
	public void run() {
		for(int numGasto = 0; numGasto< miParte; numGasto++) {
			cuenta.incrementarGasto();
			gastosRealizados++;
//			System.out.println(
//					String.format("Hilo %s, incrementando gasto: %d",
//							this.getName(),
//							gastosRealizados));
		}
System.out.println(
		String.format("Hilo %s, el total de gastos realizados es: %d",
					this.getName(), this.gastosRealizados));
	}

	public Cliente(int miParte, int gastosRealizados, CuentaBancaria cuenta) {
		super();
		this.miParte = miParte;
		this.gastosRealizados = gastosRealizados;
		this.cuenta = cuenta;
	}

	
}







