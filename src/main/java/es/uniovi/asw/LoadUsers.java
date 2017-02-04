package es.uniovi.asw;

import java.util.*;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.*;
import es.uniovi.asw.parser.writer.*;
import es.uniovi.asw.util.Printer;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... args) throws CitizenException {
		try {
			final LoadUsers runner = new LoadUsers();
			runner.run(args);
		} catch (Exception e) {
			Printer.printCitizenException(e);
		}
	}

	void run(String... args) throws CitizenException {
		String fichero = "..\\citizensLoader4b\\src\\test\\resources\\test.xlsx";
		ReadCitizens leer = new RCitizens();
		List<Citizen> citizens = new ArrayList<Citizen>();
		citizens = leer.readCitizens(fichero, fichero);
		Letter letterTxt = new TXTLetter();
		letterTxt = LetterWriter.generate("txt");
		Printer.imprimirCitizen(citizens, letterTxt);
	}

}
