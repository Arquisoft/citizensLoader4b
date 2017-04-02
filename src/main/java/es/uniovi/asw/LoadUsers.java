package es.uniovi.asw;

import es.uniovi.asw.business.parser.impl.InsertR;
import es.uniovi.asw.business.parser.writer.*;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.util.CitizenException;
import es.uniovi.asw.util.Printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LoadUsers {

	@Autowired
	private Factories factories;

	public static void main(String[] args) {
		SpringApplication.run(LoadUsers.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			try {
				if (args[0].equals("-help")) {
					System.out.println(
							"Debe añadir los ficheros de los datos de usuarios a "
									+ "la carpeta archivosExcel.\n"
									+ "Y ejecutar sin argumentos ejemplo:\n\t"
									+ "java -jar target\\citizensLoader4b-0.0.1-"
									+ "jar-with-dependecies.jar");
				} else {
					System.out.println("La orden: " + args[0]
							+ " no ha sido reconocida. \n"
							+ "Consulte la ayuda con -help.");
				}
			} catch (Exception e) {
				try {
					List<Citizen> citizens = factories.getServicesFactory()
							.getCitizenService().findAll();
					System.out.println(citizens.size());
					generarCartas(citizens);
					new InsertR().save(factories, citizens);
				} catch (Exception e1) {
					new Printer().printCitizenException(e1);
				}
			}

		};
	}

	private void generarCartas(List<Citizen> citizens) throws CitizenException {
		Letter letterTxt = new TXTLetter();
		letterTxt = FactoryLetter.generate("txt");

		Letter letterPDF = new PDFLetter();
		letterPDF = FactoryLetter.generate("pdf");

		Letter letterWord = new WordLetter();
		letterWord = FactoryLetter.generate("word");

		for (Citizen citizen : citizens) {
			letterTxt.generateLetter(citizen);
			letterPDF.generateLetter(citizen);
			letterWord.generateLetter(citizen);
		}
	}

}
