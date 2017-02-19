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
	
	private static String ruta = "..\\citizensLoader4b\\src\\test\\resources\\test.xlsx";
	
	public static void main(String... args) throws CitizenException {
		try {
			final LoadUsers runner = new LoadUsers();
			runner.run(ruta);
		} catch (Exception e) {
			Printer.printCitizenException(e);
		}
	}

	private void run(String ruta) throws CitizenException {
		if (ruta.length() == 0) {
			throw new CitizenException("No se ha especificado la ruta de acceso al " + "archivo correctamente.");

		} else {
			List<Citizen> citizens = leerFichero(ruta);
			// Se deben generar las cartas para los ciudadanos insertados
			// correctamente

			generarCartas(citizens);
			// generarCartas(new InsertR().save(citizens));
			Printer.imprimirCitizen(citizens);
		}
	}

	private List<Citizen> leerFichero(String ruta) throws CitizenException {
		ReadCitizens leer = new RCitizens();
		return leer.readCitizens(ruta);
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
