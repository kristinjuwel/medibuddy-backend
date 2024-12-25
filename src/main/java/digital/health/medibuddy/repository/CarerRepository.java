package digital.health.medibuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.Carer;

public interface CarerRepository extends JpaRepository<Carer, Long>{
	List<Carer> findByUserId(Long userId);
	Carer findByCarerId(Long carerId);
}
