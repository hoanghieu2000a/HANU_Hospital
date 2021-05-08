package se2.hanu_hospital.medical_procedure;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProcedureRepository extends JpaRepository<MedicalProcedure, Long> {

    MedicalProcedure getProcedureById(Long id);
}
