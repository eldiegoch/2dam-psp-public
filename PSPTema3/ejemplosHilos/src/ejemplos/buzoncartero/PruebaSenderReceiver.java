package ejemplos.buzoncartero;

public class PruebaSenderReceiver {

	public static void main(String[] args) {
		Buzon data = new Buzon();
	    Thread sender = new Thread(new Cartero(data));
	    sender.setName("Sender");
	    Thread receiver = new Thread(new DuenioCasa(data));
	    
	    receiver.setName("Receiver");
	    sender.start();
	    receiver.start();
//
	}

}
