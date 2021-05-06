package se2.hanu_hospital.domain.patient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.domain.patient.dto.CreatePatientDTO;
import se2.hanu_hospital.domain.patient.dto.UpdatePatientDTO;
import se2.hanu_hospital.domain.patient.entity.Patient;
//import vn.daoanhthanh.hanu_hospital_my_part.domain.patient.dto.CreatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.domain.patient.dto.UpdatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.domain.patient.entity.Patient;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }


//    @Autowired
//    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
//        this.patientRepository = patientRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public Patient create(CreatePatientDTO createPatientDTO) {
        Patient patient = modelMapper.map(createPatientDTO, Patient.class);
        return patientRepository.save(patient);
    }

    @Override
    public Patient updateById(Long id, UpdatePatientDTO updatePatientDTO) {

        Patient patient = patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updatePatientDTO, patient);
        return patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Page<Patient> findAllByKeyword(String keyword, Pageable pageable) {
        return patientRepository.findAllByNameLike(keyword, pageable);
    }

    @Override
    public List<Patient> findByKeyword(String keyword) {
        return patientRepository.findAllByNameLike(keyword);
    }
}

