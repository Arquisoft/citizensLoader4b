package es.uniovi.asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import es.uniovi.asw.util.CitizenException;

/**
 * Main application
 * 
 * @author Jorge Rodríguez Fernández
 * @author Adrián García Lumbreras
 * @author Iván González Mahagamage
 * @author Raúl Gómez Pérez
 *
 */
@SpringBootApplication
public class LoadUsers {
	public static void main(String... args) throws CitizenException {
		SpringApplication.run(LoadUsers.class);
	}
}
