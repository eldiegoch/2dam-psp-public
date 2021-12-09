package ejemplos.compradorcompulsivo;

import java.util.concurrent.atomic.AtomicInteger;

public class Cuenta {

	private int saldo;
	private AtomicInteger saldoAtomico;

	public Cuenta(int saldoCuenta) {
		this.saldo = saldoCuenta;
		this.saldoAtomico = new AtomicInteger(saldoCuenta);
	}

	public int getSaldo() {
		return saldo;
	}

	private void restar(int cantidad) {
		saldo = saldo - cantidad;
		saldoAtomico.getAndDecrement();
	}

	public void retirarDinero(int cantidad, String nombre) {
		if(getSaldo() >= cantidad) {
			System.out.println(nombre + " VA A RETIRAR SALDO (ACTUAL ES: " + getSaldo());
			
			this.restar(cantidad);
			
			System.out.println("\t" + nombre + " retira => " + cantidad + " ACTUAL ( " + this.getSaldo() + ")" );
		}else {
			System.out.println(nombre + " No puede retirar dinero, NO HAY SALDO ( " + this.getSaldo() + ")" );
		}
		
		if(getSaldo() < 0 ) {
			System.out.println("LA CUENTA HA QUEDADO EN SALDO NEGATIVO => " + this.getSaldo());
		}
	}
}
