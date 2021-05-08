package se2.hanu_hospital.staff.nurse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se2.hanu_hospital.staff.nurse.model.Nurse;

import java.util.List;


public interface NurseRepository extends JpaRepository<Nurse, Long> {
    @Query(value = "SELECT * from nurse", nativeQuery = true)
    List<Nurse> getAllNurse();

    Nurse getNurseById(Long id);
}
