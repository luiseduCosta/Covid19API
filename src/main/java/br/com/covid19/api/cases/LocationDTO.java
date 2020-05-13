package br.com.covid19.api.cases;

import lombok.Data;

@Data
public abstract class LocationDTO {
	private Long id;
	private String name;
	private Long confirmed;
	private Long recovered;
	private Long deaths;
}
