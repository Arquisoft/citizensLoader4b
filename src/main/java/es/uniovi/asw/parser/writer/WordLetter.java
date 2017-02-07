package es.uniovi.asw.parser.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.*;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class WordLetter implements Letter {

	private XWPFDocument documento;

	@Override
	public void generateLetter(Citizen citizen) throws CitizenException {
		documento = new XWPFDocument();
		FileOutputStream letter = null;

		try {

			File folder = new File("Letter/WORD");
			folder.mkdir();
			letter = new FileOutputStream("Letter/WORD/" + citizen.getDni() + ".docx");

			XWPFParagraph paragraph = documento.createParagraph();
			XWPFRun run = paragraph.createRun();

			run.setText("Usuario: " + citizen.getDni());
			run.addBreak();
			run.addBreak();
			run.setText("Password: " + citizen.getPassword());

			documento.write(letter);

		} catch (IOException e) {
			throw new CitizenException(
					"[ERROR] No se ha podido generar la carta en [WORD] para el usuario " + citizen.getDni());
		} finally {
			try {
				if (letter != null) {
					letter.close();
					System.out.println("Generada la carta en formato [WORD] para [" + citizen.getDni() + "].");
				}
			} catch (Exception e) {
				throw new CitizenException("[ERROR] [WORD] " + citizen.getDni() + ": " + e.getMessage());
			}
		}
	}

}