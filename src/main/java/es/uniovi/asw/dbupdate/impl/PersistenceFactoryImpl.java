package es.uniovi.asw.dbupdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import es.uniovi.asw.dbupdate.CitizenRepository;
import es.uniovi.asw.dbupdate.PersistenceFactory;

@Service
@EnableJpaRepositories("es.uniovi.asw.dbupdate")
public class PersistenceFactoryImpl implements PersistenceFactory {

	@Autowired
	private CitizenRepository citizenRepository;

	@Override
	public CitizenRepository newCitizenRepository() {
		return citizenRepository;
	}

}
