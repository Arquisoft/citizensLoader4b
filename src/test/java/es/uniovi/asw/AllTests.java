package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uniovi.asw.parser.LoadFromExcelTest;
import es.uniovi.asw.parser.RCitizensTest;
import es.uniovi.asw.parser.TemplateWritterTest;
import es.uniovi.asw.util.FactoryCarpetasTest;
import es.uniovi.asw.util.LogTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	FactoryCarpetasTest.class, 
	LoadUsersTest.class,
	RCitizensTest.class, 
	TemplateWritterTest.class, 
	LoadFromExcelTest.class,
	LogTest.class })
public class AllTests {
}
