package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.InsertP;
import es.uniovi.asw.model.Citizen;

/**
 * Clase encargada de enviar los ciudadanos a insertar al DBUpdate.
 * 
 * @author Raúl Gómez Pérez
 *
 */
public class InsertR implements Insert {

	@Override
	public List<Citizen> save(List<Citizen> citizens) throws CitizenException {
		return new InsertP().save(citizens);
	}

}
