package es.uniovi.asw.util;

import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.writer.Letter;

public class Printer {

	public static void printBusinessException(Exception e) {
		System.out.println(e.getLocalizedMessage());
	}

	public static void imprimirCitizen(List<Citizen> citizens, Letter letterTxt)
			throws CitizenException {
		for (Citizen citizen : citizens) {
			System.out.println("ID: " + citizen.getId());
			System.out.println("DNI: " + citizen.getDni());
			System.out.println("PASS: " + citizen.getPassword());
			letterTxt.generateLetter(citizen);
		}
	}

}
