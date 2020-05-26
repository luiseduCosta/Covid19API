package br.com.covid19.api.cases.state_case;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.covid19.api.cases.Location;
import br.com.covid19.api.cases.country_case.CountryCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "state_case")
@Getter @Setter @NoArgsConstructor
public class StateCase extends Location {
	@ManyToOne
	@JoinColumn(name = "country_case_id", nullable=false)
	private CountryCase countryCase;
	
	public StateCase(Long id, String dataId, String name, Long confirmed, 
			Long recovered, Long deaths, CountryCase countryCase) {		
		super(id, dataId, name, confirmed, recovered, deaths);
		this.countryCase = countryCase;
	}
}
