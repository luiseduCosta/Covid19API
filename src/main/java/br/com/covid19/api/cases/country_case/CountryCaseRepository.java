package br.com.covid19.api.cases.country_case;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryCaseRepository extends JpaRepository<CountryCase, Long> {

}
