package br.com.covid19.api.cases.country_case;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.covid19.api.cases.MyPage;
import br.com.covid19.api.cases.state_case.StateCaseDTO;
import br.com.covid19.api.infra.exception.ObjectNotFoundException;

@Service
public class CountryCaseService {
	
	@Autowired
	private CountryCaseRepository repository;
	
	public CountryCaseDTO getById(Long id) {
		return repository
				.findById(id)
				.map(CountryCaseDTO::create)
				.orElseThrow(
					() -> new ObjectNotFoundException("Registro não encontrado")
				);
	}
	
	public CountryCase findByDataId(String dataId) {
		return repository.findByDataId(dataId);
	}
	
	
	public List<CountryCaseDTO> findByNameBeginsWith(String name) {
		List<CountryCase> countryCases = repository.findByNameBeginsWith(name);
		
		if (countryCases.size() == 0)
			throw new NoSuchElementException("No value present");
		
		return countryCases
				.stream()
				.map(CountryCaseDTO::create)
				.collect(Collectors.toList());
	} 
	
	public List<StateCaseDTO> getStatesByCounty(Long country_id) {
		Optional<CountryCase> c = repository.findById(country_id);
		
		if (!c.isPresent())
			throw new NoSuchElementException("No value present");
		
		c.get().getStatesCases().sort(Comparator.reverseOrder());
		return c.get()
				.getStatesCases()
				.stream()
				.map(StateCaseDTO::create)
				.collect(Collectors.toList());
	}
	
	public MyPage<CountryCaseDTO> getAllCountryCase(Pageable pageable) {
		Page<CountryCase> page = repository.findAll(pageable);
		
		List<CountryCaseDTO> listCountryCaseDTO = page.get()
				.map(CountryCaseDTO::create)
				.collect(Collectors.toList());
		
		return new MyPage<CountryCaseDTO>(pageable.getPageNumber() + 1,
				page.getTotalPages(), page.getTotalElements(), listCountryCaseDTO
		);
	}
	
	public CountryCase save(CountryCase c) {
		Assert.isNull(c.getId(), "Erro ao inserir o registro, id deve ser null");
		
		return repository.save(c);
	}
	
	public CountryCase update(CountryCase c) {
		Assert.notNull(c.getDataId(), "Erro ao atualizar o registo, dataId não deve ser null");
		
		CountryCase countryCaseDb = repository.findByDataId(c.getDataId());
		if (countryCaseDb == null) {
			return null;
		}
		
		countryCaseDb.setName(c.getName());
		countryCaseDb.setConfirmed(c.getConfirmed());
		countryCaseDb.setRecovered(c.getRecovered());
		countryCaseDb.setDeaths(c.getDeaths());
		countryCaseDb.setUrlFlag(c.getUrlFlag());
		
		return repository.save(countryCaseDb);
	}
}
