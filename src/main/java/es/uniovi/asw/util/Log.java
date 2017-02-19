package es.uniovi.asw.util;

import java.io.*;
import java.util.GregorianCalendar;

import es.uniovi.asw.common.CitizenException;;

/**
 * Clase encargar de documentar y guardar todas las excepciones ocurridas con el
 * usa del programa.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Log {

	/**
	 * Método que modifica el archivo LOG.txt para añadir una nueva excepción.
	 * 
	 * @param error
	 *            Mensaje de error devuelto por la excepción.
	 * @throws CitizenException
	 *             Cualquier problema ocurrido durante la ejecución del método.
	 */
	public static void grabarError(String error) throws CitizenException {
		if ("".equals(error))
			throw new CitizenException(
					"El error a guardar en el fichero Log no puede ser vacio.");
		if (error == null)
			throw new CitizenException(
					"El error a guardar en el fichero Log no puede ser null.");
		FactoryCarpetas.crearCarpeta("Log");
		try {
			String mensajeLog = "(";
			mensajeLog += GregorianCalendar.getInstance().getTime() + ") -> ";
			mensajeLog += error + "\n";
			BufferedWriter fichero = new BufferedWriter(
					new FileWriter("..\\citizensLoader4b\\Log\\LOG.txt", true));
			fichero.write(mensajeLog);
			fichero.close();
		} catch (IOException ioe) {
			throw new CitizenException(ioe.getLocalizedMessage());
		}

	}
}
