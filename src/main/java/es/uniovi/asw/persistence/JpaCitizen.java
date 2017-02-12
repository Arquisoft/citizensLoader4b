package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uniovi.asw.model.Citizen;

public interface JpaCitizen extends JpaRepository<Citizen, Long> {


}
