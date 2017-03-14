package es.uniovi.asw.dbupdate.business;

import java.util.List;

import es.uniovi.asw.dbupdate.model.Citizen;
import es.uniovi.asw.util.CitizenException;

public interface CitizenService {
	List<Citizen> findAll() throws CitizenException;
}
