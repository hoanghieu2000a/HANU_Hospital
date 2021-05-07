package se2.hanu_hospital.medical_procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;

import java.util.List;


public interface ProcedureRepository extends JpaRepository<MedicalProcedure, Long> {

}
