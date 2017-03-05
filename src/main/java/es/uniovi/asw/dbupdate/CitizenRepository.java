package es.uniovi.asw.dbupdate;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.model.Citizen;

public interface CitizenRepository extends CrudRepository<Citizen, Long> {

	Citizen findByEmail(String email);

	Citizen findByDni(String dni);

}
