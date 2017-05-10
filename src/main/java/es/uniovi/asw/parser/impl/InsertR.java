package es.uniovi.asw.parser.impl;

import es.uniovi.asw.dbupdate.impl.InsertP;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;
import es.uniovi.asw.parser.Insert;

import java.util.List;

/**
 * Created by igm1990 on 15/03/2017.
 */
public class InsertR implements Insert {

	@Override
	public void save(Factories factories, List<Citizen> citizens)
			throws CitizenException {
		for (Citizen citizen : citizens)
			new InsertP().save(factories, citizen);

	}
}
