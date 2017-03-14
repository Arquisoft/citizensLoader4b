package es.uniovi.asw.dbupdate.business.impl.classes;

import java.io.File;
import java.util.List;

import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.parser.RCitizens;
import es.uniovi.asw.parser.ReadCitizens;
import es.uniovi.asw.parser.writer.FactoryLetter;
import es.uniovi.asw.parser.writer.Letter;
import es.uniovi.asw.parser.writer.PDFLetter;
import es.uniovi.asw.parser.writer.TXTLetter;
import es.uniovi.asw.parser.writer.WordLetter;
import es.uniovi.asw.util.CitizenException;
import es.uniovi.asw.util.FactoryCarpetas;

public class CitizenFind {
	public List<Citizen> findAll() throws CitizenException {
		List<Citizen> citizens = run();
		generarCartas(citizens);
		return citizens;
	}

	private List<Citizen> run() throws CitizenException {
		String directorio = "archivosExcel";
		File f = new File(directorio);
		List<Citizen> citizens = null;
		if (f.exists()) {
			File[] ficheros = f.listFiles();
			if (ficheros.length == 0) {
				System.out.println("Consulte el manual de uso con -help.");
			} else {
				for (int i = 0; i < ficheros.length; i++) {
					citizens = leerFichero(ficheros[i]);
				}
			}
		} else {
			new FactoryCarpetas().crearCarpeta("archivosExcel");
			System.out.println("Consulte el manual de ayuda con -help.");
			throw new CitizenException(
					"No se encuentra la carpeta archivosExcel."
							+ "\nSe ha creado automáticamente.");
		}
		return citizens;

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
