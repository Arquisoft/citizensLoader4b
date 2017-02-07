package es.uniovi.asw.parser.writer;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.util.FactoryCarpetas;

public class FactoryLetter {

	public static Letter generate(String formato) throws CitizenException {
		FactoryCarpetas.crearCarpeta("Letter/" + formato);
		return crearCarta(formato.toLowerCase());
	}

	private static Letter crearCarta(String formato) throws CitizenException {
		if (formato.equals("txt")) {
			return new TXTLetter();
		} else if (formato.equals("pdf")) {
			return new PDFLetter();
		} else if (formato.equals("word")) {
			return new WordLetter();
		} else {
			throw new CitizenException(
					"ERROR. Formato (" + formato + ") no soportado");
		}
	}
}
