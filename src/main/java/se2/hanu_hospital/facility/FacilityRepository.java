package se2.hanu_hospital.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import se2.hanu_hospital.facility.entity.Facility;

import java.util.List;


public interface FacilityRepository extends JpaRepository<Facility, Long>{
    List<Facility> findByCode(String code);
}
