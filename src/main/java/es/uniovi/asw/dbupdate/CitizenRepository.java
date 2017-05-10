package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}
