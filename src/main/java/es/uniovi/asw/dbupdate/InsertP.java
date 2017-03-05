package es.uniovi.asw.dbupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

/**
 * Clase que verifica los datos de entrada y si falta algún atributo obligatorio
 * genera el correspondiente error.
 * 
 * @author Raúl Gómez Pérez
 *
 */
@Configuration
@EnableAutoConfiguration
public class InsertP implements Insert {

	@Override
	public List<Citizen> save(List<Citizen> citizens) throws CitizenException {
		ConfigurableApplicationContext context = SpringApplication
				.run(InsertP.class);
		CitizenRepository repository = context
				.getBean(CitizenRepository.class);
		List<Citizen> addedCitizens = new ArrayList<Citizen>();
		for (Citizen citizen : citizens) {
			try {
				if (citizen != null) {
					repository.save(citizen);
					addedCitizens.add(citizen);
				}
			} catch (DataIntegrityViolationException e) {
				// Falta reportar cuando un ciudadano no se puede insertar
				throw new CitizenException(
						"un ciudadano no se puede insertar ->" + citizen);
			} catch (Exception e2) {
				// Error con la base de datos
				throw new CitizenException("Error con la base de datos");
			}
		}
		return addedCitizens;
	}

}
