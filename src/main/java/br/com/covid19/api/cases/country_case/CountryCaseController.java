package br.com.covid19.api.cases.country_case;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/covid19/countryCase")
public class CountryCaseController {
	
	@Autowired
	private CountryCaseService service;
	
	@GetMapping
	public List<CountryCaseDTO> getAll() {
		return service.getAllCountryCase();
	}
	
	@GetMapping("/{id}")
	public CountryCaseDTO getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@PostMapping
	public ResponseEntity<CountryCaseDTO> save(@RequestBody CountryCaseDTO c) {
		CountryCaseDTO countryCaseDTO = service.save(c);
		return ResponseEntity.ok(countryCaseDTO);
	}
}
