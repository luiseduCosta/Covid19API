package br.com.covid19.api.cases.country_case;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryCaseRepository extends JpaRepository<CountryCase, Long> {
	CountryCase findByDataId(String dataId);
	
	@Query("SELECT c FROM country_case c WHERE c.name like ?1%")
	List<CountryCase> findByNameBeginsWith(String name);
}
