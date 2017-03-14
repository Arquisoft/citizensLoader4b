package es.uniovi.asw.dbupdate.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uniovi.asw.dbupdate.business.CitizenService;
import es.uniovi.asw.dbupdate.business.impl.classes.CitizenFind;
import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.util.CitizenException;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Override
	public List<Citizen> findAll() throws CitizenException {
		return new CitizenFind().findAll();
	}

}
