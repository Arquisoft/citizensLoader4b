package es.uniovi.asw.parser.writer;

import es.uniovi.asw.util.FactoryCarpetas;

public class LetterWriter {

	public static Letter generate(String formato) throws Exception {
		FactoryCarpetas.crearCarpeta("Letter");
		return FactoryLetter.generate(formato);
	}
}
