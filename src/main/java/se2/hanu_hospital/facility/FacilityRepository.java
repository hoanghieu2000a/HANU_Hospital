package se2.hanu_hospital.facility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FacilityRepository extends JpaRepository<Facility, Long>{
    List<Facility> findByName(String name);

    Facility getFacilityById(Long id);
}
