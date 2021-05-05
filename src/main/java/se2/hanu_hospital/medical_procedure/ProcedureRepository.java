package se2.hanu_hospital.medical_procedure;

import org.springframework.data.jpa.repository.JpaRepository;

import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;

public interface ProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
    MedicalProcedure findByPatientName(String patientName);
}
