package br.com.covid19.api.cases.state_case;

import org.modelmapper.ModelMapper;

import br.com.covid19.api.cases.LocationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StateCaseDTO extends LocationDTO {
	private Long countryCaseId;
	
	public static StateCaseDTO create(StateCase s) {
		StateCaseDTO stateDTO = new ModelMapper().map(s, StateCaseDTO.class);
		stateDTO.setCountryCaseId(s.getCountryCase().getId());
		return stateDTO;
	}
}

