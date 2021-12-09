package ejemplos.buzoncartero.multiple;

public class PruebaSenderReceiver {

	public static void main(String[] args) {
		Buzon data = new Buzon();
		Thread sender = new Thread(new Cartero(data));
		sender.start();

		new Thread(new DuenioCasa(data,"1")).start();
		new Thread(new DuenioCasa(data,"2")).start();
		new Thread(new DuenioCasa(data,"3")).start();
		new Thread(new DuenioCasa(data,"4")).start();

	}

}
