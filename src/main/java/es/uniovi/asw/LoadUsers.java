package es.uniovi.asw;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.*;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... args) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	// TODO
	void run(String... args) {
		System.out.println("TODO TODO");
		System.out.println("A ver si funciona esto");
		String fichero = "..\\citizensLoader4b\\src\\test\\resources\\test.xlsx";
		ReadCitizens leer = new RCitizens();
		List<Citizen> citizens = new ArrayList<Citizen>();
		
		try {
			citizens = leer.readCitizens(fichero, fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Citizen citizen: citizens) {
			System.out.println("ID: " + citizen.getId());
			System.out.println("DNI: " + citizen.getDni());
			System.out.println("PASS: " +citizen.getPassword());
		}
	}
}
