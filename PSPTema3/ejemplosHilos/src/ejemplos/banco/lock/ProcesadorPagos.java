package ejemplos.banco.lock;
import java.util.ArrayList;
import java.util.List;
public class ProcesadorPagos {
	private static final int NUM_TOTAL_GASTOS = 100000;
	private static final int NUM_CLIENTES = 10;
	public static void main(String[] args) {
		CuentaBancaria cuenta = new CuentaBancaria();
		List<Cliente> clientes = new ArrayList<>();

		for(int numCliente = 1; numCliente <= NUM_CLIENTES; numCliente++) {
			//Creamos un cliente nuevo, dandole el numero, la cantidad de gastos 
			//que tiene que hacer (NUM_TOTAL / NUM_CLIENTES)
			//y la referencia a la cuenta bancaria
			Cliente nuevoCliente = 
					new Cliente(NUM_TOTAL_GASTOS/NUM_CLIENTES,
								 0,
								cuenta);
			nuevoCliente.setName(String.valueOf(numCliente));
			clientes.add(nuevoCliente);
		}
		//Itero toda la lista lanzando todos los hilos
		for (Cliente cliente : clientes) {
			cliente.start();
		}
		//Espero su terminacion
		for (Cliente cliente : clientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
System.out.println("Terminado. La cuenta bancaria tiene " + cuenta.getGastos() + " gastos");
	}

}












