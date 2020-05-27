package br.com.covid19.api.cases.country_case;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.covid19.api.cases.MyPage;
import br.com.covid19.api.cases.state_case.StateCaseDTO;

@RestController
@RequestMapping("/api/v1/covid19/countryCase")
public class CountryCaseController {
	
	@Autowired
	private CountryCaseService service;

	@GetMapping
	public MyPage<CountryCaseDTO> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
									       @RequestParam(value = "size", defaultValue = "10") Integer size) {
		return service.getAllCountryCase(PageRequest.of(page - 1, size));
	}
	
	@GetMapping("/{id}")
	public CountryCaseDTO getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/findName/{name}")
	public List<CountryCaseDTO> getCountryByName(@PathVariable("name") String name) {
		return service.findByNameBeginsWith(name);
	}
	
	@GetMapping("/stateCases/{country_id}")
	public List<StateCaseDTO> getStatesByCountry(@PathVariable("country_id") Long id) {
		return service.getStatesByCounty(id);
	}
}
