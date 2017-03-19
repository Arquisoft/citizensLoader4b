package es.uniovi.asw.persistence;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;

/**
 * Created by igm1990 on 15/03/2017.
 */
public interface Insert {
	public void save(Factories factories, Citizen citizen);
}
