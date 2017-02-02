package es.uniovi.asw.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.uniovi.asw.model.Citizen;

public interface JpaCitizen extends JpaRepository<Citizen, Long> {

	public List<Citizen> save(List<Citizen> citizens);

}
