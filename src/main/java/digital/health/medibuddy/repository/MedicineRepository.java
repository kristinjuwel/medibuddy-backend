package digital.health.medibuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	Medicine findByMedId(Long medId);
	List<Medicine> findByUserId(Long userId);
	List<Medicine> findAll();
}
