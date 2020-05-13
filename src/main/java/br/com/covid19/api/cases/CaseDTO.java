package br.com.covid19.api.cases;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class CaseDTO {
	private Long id;
	private String location;
	private Long confirmed;
	private Long recovered;
	private Long deaths;
	
	public static CaseDTO crerate(Case c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CaseDTO.class);
	}
}
