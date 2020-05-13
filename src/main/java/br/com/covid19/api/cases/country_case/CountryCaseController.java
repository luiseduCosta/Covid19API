package br.com.covid19.api.cases.country_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/covid19/countryCase")
public class CountryCaseController {
	
	@Autowired
	private CountryCaseService service;
	
	@PostMapping
	public ResponseEntity<CountryCaseDTO> salvar(@RequestBody CountryCaseDTO c) {
		CountryCaseDTO countryCaseDTO = service.salvar(c);
		return ResponseEntity.ok(countryCaseDTO);
	}
}
