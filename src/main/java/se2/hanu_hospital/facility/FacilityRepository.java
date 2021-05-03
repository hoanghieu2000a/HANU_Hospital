package se2.hanu_hospital.facility;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se2.hanu_hospital.facility.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long>{
    List<Facility> findByCode(String code);
}
