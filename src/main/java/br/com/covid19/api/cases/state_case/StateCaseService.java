package br.com.covid19.api.cases.state_case;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateCaseService {
	
	@Autowired
	private StateCaseRepository repository;
	
	public StateCase findByDataId(String dataId) {
		return repository.findByDataId(dataId);
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
