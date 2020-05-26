package br.com.covid19.api.cases.country_case;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.covid19.api.cases.state_case.StateCaseDTO;

@RestController
@RequestMapping("/api/v1/covid19/countryCase")
public class CountryCaseController {
	
	@Autowired
	private CountryCaseService service;

	@GetMapping
	public List<CountryCaseDTO> getAll() {
		return service.getAllCountryCase();
	}
	
	@GetMapping("/findId")
	public CountryCaseDTO getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/findName")
	public List<CountryCaseDTO> getCountryByName(@RequestParam("name") String name) {
		return service.findByNameBeginsWith(name);
	}
	
	@GetMapping("/stateCases")
	public List<StateCaseDTO> getStatesByCountry(@RequestParam("country_id") Long id) {
		return service.getStatesByCounty(id);
	}
}
