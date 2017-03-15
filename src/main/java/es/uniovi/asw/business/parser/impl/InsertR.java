package es.uniovi.asw.business.parser.impl;

import es.uniovi.asw.business.parser.Insert;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.impl.InsertP;
import es.uniovi.asw.util.CitizenException;

import java.util.List;

/**
 * Created by igm1990 on 15/03/2017.
 */
public class InsertR implements Insert {

    @Override
    public void save(Factories factories, List<Citizen> citizens) throws CitizenException {
        for(Citizen citizen: citizens)
            new InsertP().save(factories, citizen);
    }
}
