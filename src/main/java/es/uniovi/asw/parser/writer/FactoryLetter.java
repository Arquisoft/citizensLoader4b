package es.uniovi.asw.parser.writer;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.util.FactoryCarpetas;

public class FactoryLetter {

	public static Letter generate(String formato) throws CitizenException {
		FactoryCarpetas.crearCarpeta("Letter");
		FactoryCarpetas.crearCarpeta("Letter\\" + formato.toUpperCase());
		return crearCarta(formato.toLowerCase());
	}

	private static Letter crearCarta(String formato) throws CitizenException {
		if ("txt".equals(formato)) {
			return new TXTLetter();
		} else if ("pdf".equals(formato)) {
			return new PDFLetter();
		} else if ("word".equals(formato)) {
			return new WordLetter();
		} else {
			throw new CitizenException(
					"ERROR. Formato (" + formato + ") no soportado");
		}
	}
}
