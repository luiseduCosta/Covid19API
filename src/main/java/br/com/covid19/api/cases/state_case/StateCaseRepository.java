package br.com.covid19.api.cases.state_case;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateCaseRepository extends JpaRepository<StateCase, Long> {
	StateCase findByDataId(String dataId);
}
