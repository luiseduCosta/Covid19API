package br.com.covid19.api.cases.country_case;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.modelmapper.ModelMapper;

import br.com.covid19.api.cases.Location;
import br.com.covid19.api.cases.state_case.StateCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "country_case")
@Getter @Setter @NoArgsConstructor
public class CountryCase extends Location {
	private String urlFlag;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "countryCase")
	private List<StateCase> statesCases;

	public CountryCase(Long id, String dataId, String name, Long confirmed, 
			Long recovered, Long deaths, String urlFlag) {		
		super(id, dataId, name, confirmed, recovered, deaths);
		this.urlFlag = urlFlag;
	}
	
	public static CountryCase create(CountryCaseDTO c) {
		return new ModelMapper().map(c, CountryCase.class);
	}
}

