package es.uniovi.asw.parser;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.dbupdate.presentation.Insert;
import es.uniovi.asw.dbupdate.presentation.InsertP;
import es.uniovi.asw.util.CitizenException;

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
