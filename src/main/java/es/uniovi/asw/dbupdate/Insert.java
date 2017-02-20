package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

/**
 * Interfaz. Recibe un objeto con la información para insertar en la base de
 * datos.
 * 
 * @author Raúl Gómez Pérez
 *
 */
public interface Insert {

	public List<Citizen> save(List<Citizen> citizens) throws CitizenException;

}
