package es.uniovi.asw.dbupdate.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.dbupdate.model.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}
