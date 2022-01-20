package com.psp.tema5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        log.info("Mensajillo de prueba de INFO");
        log.debug("Mensajillo de prueba de DEBUG");
    }
}
