package se2.hanu.hospital.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se2.hanu.hospital.base.CRUDService;
import se2.hanu.hospital.domain.patient.dto.CreatePatientDTO;
import se2.hanu.hospital.domain.patient.dto.UpdatePatientDTO;
import se2.hanu.hospital.domain.patient.entity.Patient;

import java.util.List;

public interface PatientService extends CRUDService<Patient, Long, CreatePatientDTO, UpdatePatientDTO> {
    boolean isCodeUnique(String code);
    Page<Patient> findAllByKeyword(String keyword, Pageable pageable);
    List<Patient> findByKeyword(String keyword);
}
