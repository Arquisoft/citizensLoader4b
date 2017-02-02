package es.uniovi.asw.parser.writer;

import java.io.*;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class TXTLetter implements Letter {

	@Override
	public void generateLetter(Citizen citizen) throws CitizenException {
		FileWriter writer = null;
		File letter = new File("Letter/" + citizen.getDni() + ".txt");
		try {
			writer = new FileWriter(letter);
			writer.write("Usuario: " + citizen.getDni() + "\n" + "Password: "
					+ citizen.getPassword());
			writer.close();
		} catch (IOException e) {
			throw new CitizenException(
					"ERROR. No se ha podido generar la carta en TXT para el usuario "
							+ citizen.getDni());
		}
	}
}
