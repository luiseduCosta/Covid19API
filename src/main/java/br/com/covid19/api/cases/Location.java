package br.com.covid19.api.cases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Location implements Comparable<Location> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dataId;
	private String name;
	private Long confirmed;
	private Long recovered;
	private Long deaths;
	
	@Override
	public int compareTo(Location location) {
		if (this.getConfirmed() == null || location.getConfirmed() == null) 
			return 0;

		if (this.getConfirmed() < location.getConfirmed())
			return -1;
		else if (this.getConfirmed() > location.getConfirmed())
			return 1;
		else 
			return 0;
	}
}
