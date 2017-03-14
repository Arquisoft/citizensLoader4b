package es.uniovi.asw.parser.writer;

import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.util.CitizenException;

/**
 * Interfaz que define los métodos que usarán las distintas clase que generan
 * cartas.
 * 
 * @author Adrián García Lumbreras
 * @author Iván González Mahagamage
 *
 */
public interface Letter {

	/**
	 * Método encargado de crear las cartas en las cuales se mostrará a un
	 * ciudadano su clave de acceso y su contraseña.
	 * 
	 * @param citizen
	 *            Ciudadano al cual queremos generar su carta.
	 * @throws CitizenException
	 *             Cualquier problema ocurrido durante la ejecución del método.
	 */
	public void generateLetter(Citizen citizen) throws CitizenException;

}
