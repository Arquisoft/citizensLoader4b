package es.uniovi.asw.dbupdate.impl;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;

/**
 * Created by igm1990 on 15/03/2017.
 */
public class InsertP implements Insert {
	@Override
	public void save(Factories factories, Citizen citizen) {
		factories.getPersistenceFactory().newCitizenRepository().save(citizen);
	}
}
