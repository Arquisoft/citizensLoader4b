package es.uniovi.asw;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.*;
import es.uniovi.asw.parser.writer.*;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... args) throws Exception {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	void run(String... args) throws Exception {
		System.out.println("TODO TODO");
		System.out.println("A ver si funciona esto");
		String fichero = "..\\citizensLoader4b\\src\\test\\resources\\test.xlsx";
		ReadCitizens leer = new RCitizens();
		List<Citizen> citizens = new ArrayList<Citizen>();
		
		try {
			citizens = leer.readCitizens(fichero, fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Letter letterTxt = new TXTLetter();
		letterTxt = LetterWriter.generate("txt");
		
		for(Citizen citizen: citizens) {
			System.out.println("ID: " + citizen.getId());
			System.out.println("DNI: " + citizen.getDni());
			System.out.println("PASS: " +citizen.getPassword());
			letterTxt.generateLetter(citizen);
		}
		
	}
}
