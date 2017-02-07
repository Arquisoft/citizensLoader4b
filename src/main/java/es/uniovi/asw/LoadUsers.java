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
		List<Citizen> citizens = leerFichero();
		generarCartas(citizens);
		Printer.imprimirCitizen(citizens);
	}

	private List<Citizen> leerFichero() throws CitizenException {
		String fichero = "..\\citizensLoader4b\\src\\test\\resources\\test.xlsx";
		ReadCitizens leer = new RCitizens();
		return leer.readCitizens(fichero, fichero);
	}

	private void generarCartas(List<Citizen> citizens) throws CitizenException {
		Letter letterTxt = new TXTLetter();
		letterTxt = LetterWriter.generate("txt");

		Letter letterPDF = new PDFLetter();
		letterPDF = LetterWriter.generate("pdf");

		Letter letterWord = new WordLetter();
		letterWord = LetterWriter.generate("word");

		for (Citizen citizen : citizens) {
			letterTxt.generateLetter(citizen);
			letterPDF.generateLetter(citizen);
			letterWord.generateLetter(citizen);
		}
	}

}
