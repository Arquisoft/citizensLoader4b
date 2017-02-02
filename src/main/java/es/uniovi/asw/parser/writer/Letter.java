package es.uniovi.asw.parser.writer;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public interface Letter {

	public void generateLetter(Citizen citizen) throws CitizenException;

}
