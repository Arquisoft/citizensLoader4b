package es.uniovi.asw;

import java.util.*;

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

	public static void main(String... args) throws Exception {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	void run(String... args) {
		try {
			String fichero = "..\\itizensLoader4b\\src\\test\\resources\\test.xlsx";
			ReadCitizens leer = new RCitizens();
			List<Citizen> citizens = new ArrayList<Citizen>();

			citizens = leer.readCitizens(fichero, fichero);

			Letter letterTxt = new TXTLetter();
			letterTxt = LetterWriter.generate("txt");
			Printer.imprimirCitizen(citizens, letterTxt);
		} catch (Exception e) {
			Printer.printBusinessException(e);
		}

	}

}
