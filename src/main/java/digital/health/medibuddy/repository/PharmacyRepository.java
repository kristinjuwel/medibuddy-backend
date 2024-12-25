package digital.health.medibuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long>{
	Pharmacy findByPharmId(Long pharmId);
}
