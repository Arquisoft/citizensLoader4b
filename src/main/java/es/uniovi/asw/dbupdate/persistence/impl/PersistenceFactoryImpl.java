package es.uniovi.asw.dbupdate.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import es.uniovi.asw.dbupdate.persistence.CitizenRepository;
import es.uniovi.asw.dbupdate.persistence.PersistenceFactory;

@Service
@EnableJpaRepositories("es.uniovi.asw.dbupdate.persistence")
public class PersistenceFactoryImpl implements PersistenceFactory {

	@Autowired
	private CitizenRepository citizenRepository;

	@Override
	public CitizenRepository newCitizenRepository() {
		return citizenRepository;
	}
}
