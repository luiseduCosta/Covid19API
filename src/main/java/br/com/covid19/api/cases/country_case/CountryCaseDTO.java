package br.com.covid19.api.cases.country_case;

import org.modelmapper.ModelMapper;

import br.com.covid19.api.cases.LocationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CountryCaseDTO extends LocationDTO {
	private String urlFlag;
	
	public static CountryCaseDTO create(CountryCase c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CountryCaseDTO.class);
	}
}

