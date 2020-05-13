package br.com.covid19.api.cases.country_case;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.modelmapper.ModelMapper;

import br.com.covid19.api.cases.Location;
import br.com.covid19.api.cases.state_case.StateCase;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "country_case")
@Getter @Setter
public class CountryCase extends Location {
	private String urlBandeira;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "countryCase")
	private List<StateCase> statesCases;
	
	public static CountryCase create(CountryCaseDTO c) {
		return new ModelMapper().map(c, CountryCase.class);
	}
}

