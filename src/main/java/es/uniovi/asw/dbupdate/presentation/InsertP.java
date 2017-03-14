package es.uniovi.asw.dbupdate.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbupdate.infraestructure.Factories;
import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.util.CitizenException;

/**
 * Clase que verifica los datos de entrada y si falta algún atributo obligatorio
 * genera el correspondiente error.
 * 
 * @author Raúl Gómez Pérez
 * @author Iván González Mahagamage
 *
 */

@RestController
public class InsertP implements Insert {

	@Autowired
	private Factories factories;

	@RequestMapping(value = "/TCITIZENS", method = RequestMethod.GET)
	@Override
	public List<Citizen> save(List<Citizen> citizens) throws CitizenException {
		return factories.getServicesFactory().getCitizenService().findAll();

	}

}
