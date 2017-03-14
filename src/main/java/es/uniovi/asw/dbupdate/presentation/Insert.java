package es.uniovi.asw.dbupdate.presentation;

import java.util.List;

import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.util.CitizenException;

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
