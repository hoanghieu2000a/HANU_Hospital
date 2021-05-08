package se2.hanu_hospital.staff.doctor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se2.hanu_hospital.staff.doctor.model.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "SELECT * from doctor", nativeQuery = true)
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    List<Doctor> getDoctorByAvailableIsTrue();
}
