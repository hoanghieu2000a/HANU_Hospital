package se2.hanu_hospital.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se2.hanu_hospital.domain.patient.entity.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByCode(String code);

    Page<Patient> findAllByNameLike(String partialName, Pageable pageable);

    List<Patient> findAllByNameLike(String partialName);
}
