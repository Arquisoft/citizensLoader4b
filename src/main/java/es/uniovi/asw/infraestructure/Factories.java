package es.uniovi.asw.infraestructure;

import es.uniovi.asw.business.ServicesFactory;
import es.uniovi.asw.dbupdate.PersistenceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Factories {

	@Autowired
	private ServicesFactory servicesFactory;

	@Autowired
	private PersistenceFactory persistenceFactory;

	public ServicesFactory getServicesFactory() {
		return servicesFactory;
	}

	public PersistenceFactory getPersistenceFactory() {
		return persistenceFactory;
	}

}
