package es.uniovi.asw;

import java.io.File;
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
			runner.run();
		} catch (Exception e) {
			new Printer().printCitizenException(e);
		}
	}

	private void run() throws CitizenException {
//		try {
//			if (args[0].equals(null)) {
//				throw new CitizenException(
//						"No se ha especificado la ruta de acceso al "
//								+ "archivo correctamente.");
//			} else {
//
//				List<Citizen> citizens = leerFichero(args);
//				generarCartas(citizens);
//				// generarCartas(new InsertR().save(citizens));
//				new Printer().imprimirCitizen(citizens);
//			}
//		} catch (ArrayIndexOutOfBoundsException e) {
//			throw new CitizenException(
//					"No se ha especificado la ruta de acceso al "
//							+ "archivo correctamente.");
//		}
		String directorio = "..\\citizensLoader4b\\archivosExcel";
		File f = new File(directorio);
		if (f.exists()) {
			File[] ficheros = f.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				System.out.println(ficheros[i].getName());
				List<Citizen> citizens = leerFichero(ficheros[i]);
				generarCartas(citizens);
				new Printer().imprimirCitizen(citizens);
			}
		} else {
			throw new CitizenException(
					"No se encuentra la carpeta con los archivos Excel");
		}

	}

	private List<Citizen> leerFichero(File fichero) throws CitizenException {
		ReadCitizens leer = new RCitizens();
		return leer.readCitizens(fichero);
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
