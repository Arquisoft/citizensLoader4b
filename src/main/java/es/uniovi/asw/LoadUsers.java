package es.uniovi.asw;


import es.uniovi.asw.business.parser.impl.InsertR;
import es.uniovi.asw.business.parser.writer.*;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.util.CitizenException;
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
            List<Citizen> citizens = factories.getServicesFactory()
                    .getCitizenService().findAll();
            generarCartas(citizens);
            new InsertR().save(factories, citizens);
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
