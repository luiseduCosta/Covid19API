package br.com.covid19.api.cases.country_case;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.covid19.api.cases.state_case.StateCaseDTO;
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
	
	public CountryCase findByDataId(String dataId) {
		return countryCaseRepository.findByDataId(dataId);
	}
	
	public List<StateCaseDTO> getStatesByCounty(Long country_id) {
		Optional<CountryCase> c = countryCaseRepository.findById(country_id);
	
		return c.get()
				.getStatesCases()
				.stream()
				.map(StateCaseDTO::create)
				.collect(Collectors.toList());
	}
	
	public List<CountryCaseDTO> getAllCountryCase() {
		return countryCaseRepository
				.findAll()
				.stream()
				.map(CountryCaseDTO::create)
				.collect(Collectors.toList());
	}
	
	public CountryCase save(CountryCase c) {
		Assert.isNull(c.getId(), "Erro ao inserir o registro, id deve ser null");
		
		return countryCaseRepository.save(c);
	}
	
	public CountryCase update(CountryCase c) {
		Assert.notNull(c.getDataId(), "Erro ao atualizar o registo, dataId não deve ser null");
		
		CountryCase countryCaseDb = countryCaseRepository.findByDataId(c.getDataId());
		if (countryCaseDb == null) {
			return null;
		}
		
		countryCaseDb.setName(c.getName());
		countryCaseDb.setConfirmed(c.getConfirmed());
		countryCaseDb.setRecovered(c.getRecovered());
		countryCaseDb.setDeaths(c.getDeaths());
		countryCaseDb.setUrlFlag(c.getUrlFlag());
		
		return countryCaseRepository.save(countryCaseDb);
	}
}
