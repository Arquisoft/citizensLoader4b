package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.Insert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by igm1990 on 15/03/2017.
 */
public class InsertP implements Insert {
    @Override
    public void save(Factories factories, Citizen citizen) {
        factories.getPersistenceFactory().newCitizenRepository().save
                (citizen);
    }
}
