package se2.hanu_hospital.patient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.util.Valid;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
    }

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updateById(Long id, PatientPayload patientPayload) {
        if (!patientRepository.existsById(id)) {
            throw new IllegalStateException("There is no department with that id!");
        }

        Patient patientInDB = patientRepository.getPatientById(id);
        if (Valid.stringValid(patientPayload.getName())){
            patientInDB.setName(patientPayload.getName());
        }
        if(Valid.stringValid(patientPayload.getAddress())){
            patientInDB.setAddress(patientPayload.getAddress());
        }
        if(Valid.stringValid(patientPayload.getPhoneNumber())){
            patientInDB.setPhoneNumber(patientInDB.getPhoneNumber());
        }
        if(patientPayload.getGender() != null){
            patientInDB.setGender(patientPayload.getGender());
        }
        if(patientPayload.getDob() != null){
            patientInDB.setDob(patientPayload.getDob());
        }

        return patientRepository.save(patientInDB);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Page<Patient> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public boolean isPhoneNumberUnique(String phoneNumber) {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    }

    public Page<Patient> findAllByKeyword(String keyword, Pageable pageable) {
        return patientRepository.findAllByNameLike(keyword, pageable);
    }

    public List<Patient> findByKeyword(String keyword) {
        return patientRepository.findAllByNameLike(keyword);
    }
}

