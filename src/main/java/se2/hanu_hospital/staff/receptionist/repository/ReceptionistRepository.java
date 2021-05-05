package se2.hanu_hospital.staff.receptionist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.receptionist.model.Receptionist;

import java.util.List;


public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    @Query(value = "SELECT * from receptionist", nativeQuery = true)
    List<Receptionist> getAllReceptionist();

    List<Receptionist> getReceptionistById(Long id);
}
