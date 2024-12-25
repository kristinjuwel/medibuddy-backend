package digital.health.medibuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.PharmMed;

public interface PharmMedRepository extends JpaRepository<PharmMed, Long>{
	PharmMed findByPmedId(Long pmedId);
	List<PharmMed> findByPharmId(Long pharmId);
}
