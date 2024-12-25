package digital.health.medibuddy.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.MedSched;

public interface MedSchedRepository extends JpaRepository<MedSched, Long>{
	MedSched findBySchedId(Long schedId);
	List<MedSched> findByMedId(Long medId);
	List<MedSched> findByDayAndTime(LocalDate day, LocalTime time);
}
