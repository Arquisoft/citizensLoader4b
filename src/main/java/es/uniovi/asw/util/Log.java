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
		try {
			String mensaje_log = "(";
			mensaje_log += GregorianCalendar.getInstance().getTime() + ") -> ";
			mensaje_log += error + "\n";
			BufferedWriter fichero = new BufferedWriter(
					new FileWriter("..\\citizensLoader4b\\Log\\LOG.txt", true));
			fichero.write(mensaje_log);
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			throw new CitizenException(
					"El archivo LOG no se ha podido guardar.");
		} catch (IOException ioe) {
			throw new CitizenException(
					"Error de entrada/salida en el archivo LOG.");
		}
	}
}
