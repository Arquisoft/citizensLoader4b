package es.uniovi.asw.parser.writer;

import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class PDFLetter implements Letter {

	@Override
	public void generateLetter(Citizen citizen) throws CitizenException {
		Document doc = null;
		FileOutputStream letter = null;

		try {
			letter = new FileOutputStream("Letter/" + citizen.getDni() + ".pdf");
			doc = new Document();
			PdfWriter.getInstance(doc, letter);

			doc.open();
			doc.add(new Paragraph("Usuario: " + citizen.getDni() + "\n" + "Password: "
					+ citizen.getPassword()));
			doc.close();
		} catch (FileNotFoundException | DocumentException e) {
			throw new CitizenException(
					"ERROR. No se ha podido generar la carta en PDF para el usuario " + citizen.getDni());
		}
	}

}