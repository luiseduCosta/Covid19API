package br.com.covid19.api.cases.state_case;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/covid19/stateCase")
public class StateCaseController {
	
	@Autowired
	private StateCaseService service;
	
	@GetMapping("/brazil")
	public List<StateCaseDTO> getStatesCasesBrazil() {
		return service.getStatesCasesBrazil();
	}
}
