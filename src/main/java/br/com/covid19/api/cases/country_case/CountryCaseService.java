package br.com.covid19.api.cases.country_case;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryCaseService {
	
	@Autowired
	private CountryCaseRepository countryCaseRepository;
	
	public CountryCaseDTO salvar(CountryCaseDTO c) {
		Assert.isNull(c.getId(), "Erro ao inserir o registro, id deve ser null");
		
		CountryCase countryCase = countryCaseRepository
				.save(CountryCase.create(c));
		return CountryCaseDTO.create(countryCase);
	}
}
