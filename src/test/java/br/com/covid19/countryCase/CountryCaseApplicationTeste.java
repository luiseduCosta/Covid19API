package br.com.covid19.countryCase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.covid19.api.cases.country_case.CountryCase;
import br.com.covid19.api.cases.country_case.CountryCaseService;

@SpringBootTest
public class CountryCaseApplicationTeste {
	
	@Autowired
	private CountryCaseService service;
	
	@Test
	public void getCountryCaseDataId() {
		CountryCase c = service.findByDataId("ll12");
		assertNotNull(c);
	}
}
