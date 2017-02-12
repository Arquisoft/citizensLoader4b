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

	private void run(String... args) throws CitizenException {
		if (args.length != 0) {
			List<Citizen> citizens;
			citizens = leerFichero(args[0]);
			generarCartas(citizens);
			Printer.imprimirCitizen(citizens);
		} else {
			throw new CitizenException(
					"No se ha especificado la ruta de acceso al archivo.");
		}
	}

	private List<Citizen> leerFichero(String ruta) throws CitizenException {
		ReadCitizens leer = new RCitizens();
		return leer.readCitizens(ruta, ruta);
	}

	private void generarCartas(List<Citizen> citizens) throws CitizenException {
		Letter letterTxt = new TXTLetter();
		letterTxt = FactoryLetter.generate("txt");

		Letter letterPDF = new PDFLetter();
		letterPDF = FactoryLetter.generate("pdf");

		Letter letterWord = new WordLetter();
		letterWord = FactoryLetter.generate("word");

		for (Citizen citizen : citizens) {
			letterTxt.generateLetter(citizen);
			letterPDF.generateLetter(citizen);
			letterWord.generateLetter(citizen);
		}
	}

}
