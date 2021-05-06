package se2.hanu_hospital.medical_procedure;



import se2.hanu_hospital.medical_procedure.dto.CreateProcedureDTO;
import se2.hanu_hospital.medical_procedure.dto.UpdateProcedureDTO;
import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;
import se2.hanu_hospital.util.CRUDService;

import java.util.List;


public interface ProcedureService extends CRUDService<MedicalProcedure, Long, CreateProcedureDTO, UpdateProcedureDTO> {
    List<MedicalProcedure> findByPatientName(String patientName);
}
