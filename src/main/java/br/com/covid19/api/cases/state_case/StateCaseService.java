package br.com.covid19.api.cases.state_case;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.covid19.api.cases.country_case.CountryCase;
import br.com.covid19.api.cases.country_case.CountryCaseService;

@Service
public class StateCaseService {
	
	@Autowired
	private StateCaseRepository repository;
	@Autowired
	private CountryCaseService countryCaseService;
	
	public StateCase findByDataId(String dataId) {
		return repository.findByDataId(dataId);
	}
	
	public List<StateCaseDTO> getStatesCasesBrazil() {
		CountryCase countryCase = countryCaseService.findByDataId("015fr");
		
		if (countryCase == null)
			throw new NoSuchElementException("No value present");
		
		countryCase.getStatesCases().sort(Comparator.reverseOrder());
		return countryCase.getStatesCases()
				.stream()
				.map(StateCaseDTO::create)
				.collect(Collectors.toList());
	}
	
	public StateCase save(StateCase state) {
		Assert.isNull(state.getId(), "Erro ao inserir o registro, id deve ser null");
		
		return repository.save(state);
	}
	
	public StateCase update(StateCase state) {
		Assert.notNull(state.getDataId(), "Erro ao atualizar o registo, dataId não deve ser null");
		
		StateCase stateCaseDb = repository.findByDataId(state.getDataId());
		if (stateCaseDb == null) {
			return null;
		}
		
		stateCaseDb.setName(state.getName());
		stateCaseDb.setConfirmed(state.getConfirmed());
		stateCaseDb.setRecovered(state.getRecovered());
		stateCaseDb.setDeaths(state.getDeaths());
		
		return repository.save(stateCaseDb);
	}
}
