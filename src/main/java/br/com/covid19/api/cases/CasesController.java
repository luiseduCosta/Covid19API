package br.com.covid19.api.cases;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/covid19")
public class CasesController {
	
	@GetMapping
	public String get() {
		return "API Covid19";
	}
}
