package es.uniovi.asw.parser.writer;

import java.io.File;

public class LetterWriter {

	public static Letter generate(String formato) throws Exception {
		
		Letter writer = null;
		File file = new File("Letter");
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		if (formato.equals("txt")) {
			writer = new TXTLetter();
		} else if (formato.equals("pdf")) {
			writer = new PDFLetter();
		} else if (formato.equals("word")) {
			writer = new WordLetter();
		} else {
			throw new Exception("ERROR. Formato (" + formato + ") no soportado");
		}
		
		return writer;
	}
}
