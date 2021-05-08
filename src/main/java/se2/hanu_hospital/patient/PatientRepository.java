package se2.hanu_hospital.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.entity.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByPhoneNumber(String patientPhoneNumber);

    Page<Patient> findAllByNameLike(String partialName, Pageable pageable);

    List<Patient> findAllByNameLike(String partialName);

    Patient getPatientById(Long id);
}
