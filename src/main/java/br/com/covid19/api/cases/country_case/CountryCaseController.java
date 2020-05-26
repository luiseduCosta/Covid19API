package br.com.covid19.api.cases.country_case;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.covid19.api.Crawler;
import br.com.covid19.api.cases.state_case.StateCaseDTO;

@RestController
@RequestMapping("/api/v1/covid19/countryCase")
public class CountryCaseController {
	
	@Autowired
	private CountryCaseService service;
	
	@Autowired
	private Crawler crawler;
	
	@GetMapping
	public List<CountryCaseDTO> getAll() {
		System.out.println(crawler);
		return service.getAllCountryCase();
	}
	
	@GetMapping("/{id}")
	public CountryCaseDTO getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/stateCases/{country_id}")
	public List<StateCaseDTO> getStatesByCountry(@PathVariable("country_id") Long id) {
		return service.getStatesByCounty(id);
	}
	
}
