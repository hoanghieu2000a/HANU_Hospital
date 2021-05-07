package se2.hanu_hospital.staff.nurse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se2.hanu_hospital.staff.nurse.model.Nurse;

import java.util.List;


public interface NurseRepository extends JpaRepository<Nurse, Long> {
    @Query(value = "SELECT * from receptionist", nativeQuery = true)
    List<Nurse> getAllReceptionist();

    List<Nurse> getReceptionistById(Long id);
}
