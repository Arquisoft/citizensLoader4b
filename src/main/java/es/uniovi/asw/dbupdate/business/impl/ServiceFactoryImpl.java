package es.uniovi.asw.dbupdate.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.dbupdate.business.CitizenService;
import es.uniovi.asw.dbupdate.business.ServicesFactory;

@Service
public class ServiceFactoryImpl implements ServicesFactory {

	@Autowired
	private CitizenService citizenService;

	@Override
	public CitizenService getCitizenService() {
		return citizenService;
	}

}
