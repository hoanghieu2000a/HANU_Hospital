package se2.hanu_hospital.domain.medical_procedure;

import se2.hanu_hospital.domain.medical_procedure.dto.CreateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.dto.UpdateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.entity.MedicalProcedure;
import se2.hanu_hospital.util.CRUDService;

public interface ProcedureService extends CRUDService<MedicalProcedure, Long, CreateProcedureDTO, UpdateProcedureDTO>{
    MedicalProcedure findByPatientName(String patientName);
}
