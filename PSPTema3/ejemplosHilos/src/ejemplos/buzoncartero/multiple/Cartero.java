package ejemplos.buzoncartero.multiple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cartero implements Runnable {
    private Buzon buzon;
 
    // standard constructors

	public Cartero(Buzon data) {
		super();
		this.buzon = data;
	}
    public void run() {
    	
    	List<String> cartas = new ArrayList<>();
    	for(int paquet = 1; paquet < 10; paquet++) {
    		cartas.add(String.valueOf(paquet));
    	}
    	cartas.add("End");
 
        for (String packet : cartas) {
            mensajeSender("Quiero enviar carta " + packet);

            buzon.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread interrupted: " +e);
            }
        }
        
        mensajeSender("no tengo más paquetes para enviar");
        
        //Le decimos al gestor depaquetes que no se aceptan mas paquetes
        buzon.envioTerminado();
    }
    
    private void mensajeSender(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| Cartero: " + mensaje);
		
	}

}