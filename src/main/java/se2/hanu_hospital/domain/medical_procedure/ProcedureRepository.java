package se2.hanu_hospital.domain.medical_procedure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se2.hanu_hospital.domain.medical_procedure.entity.MedicalProcedure;

public interface ProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
    List<MedicalProcedure> findByPatientName(String patientName);
}
