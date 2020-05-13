package br.com.covid19.api.cases.country_case;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.covid19.api.infra.exception.ObjectNotFoundException;

@Service
public class CountryCaseService {
	
	@Autowired
	private CountryCaseRepository countryCaseRepository;
	
	public CountryCaseDTO getById(Long id) {
		return countryCaseRepository
				.findById(id)
				.map(CountryCaseDTO::create)
				.orElseThrow(
					() -> new ObjectNotFoundException("Registro não encontrado")
				);
	}
	
	public List<CountryCaseDTO> getAllCountryCase() {
		return countryCaseRepository
				.findAll()
				.stream()
				.map(CountryCaseDTO::create)
				.collect(Collectors.toList());
	}
	
	public CountryCaseDTO save(CountryCaseDTO c) {
		Assert.isNull(c.getId(), "Erro ao inserir o registro, id deve ser null");
		
		CountryCase countryCase = countryCaseRepository
				.save(CountryCase.create(c));
		return CountryCaseDTO.create(countryCase);
	}
}
