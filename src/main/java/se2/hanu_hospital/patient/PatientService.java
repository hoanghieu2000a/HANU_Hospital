package se2.hanu_hospital.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se2.hanu_hospital.patient.dto.CreatePatientDTO;
import se2.hanu_hospital.patient.dto.UpdatePatientDTO;
import se2.hanu_hospital.patient.entity.Patient;
import se2.hanu_hospital.util.CRUDService;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.dto.CreatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.dto.UpdatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.entity.Patient;
//import vn.daoanhthanh.hanu_hospital_my_part.util.CRUDService;

import java.util.List;

public interface PatientService extends CRUDService<Patient, Long, CreatePatientDTO, UpdatePatientDTO> {
    boolean isPhoneNumberUnique(String patientPhoneNumber);
    Page<Patient> findAllByKeyword(String keyword, Pageable pageable);
    List<Patient> findByKeyword(String keyword);
}
