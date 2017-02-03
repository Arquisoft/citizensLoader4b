package es.uniovi.asw.util;

import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.writer.Letter;

/**
 * Clase encarga de mostrar por pantalla datos al usuario.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Printer {

	/**
	 * Informa al usuario de la excepción ocurrida durante la ejecución del
	 * programa y guarda dicha excepcion en el fichero LOG.txt
	 * 
	 * @param e
	 *            Excepción ocurrida
	 * @throws CitizenException
	 *             Cualquier problema ocurrido durante la ejecución del método.
	 */
	public static void printCitizenException(Exception e)
			throws CitizenException {
		Log.grabarError(e.getLocalizedMessage());
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
