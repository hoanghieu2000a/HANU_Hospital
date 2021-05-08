package se2.hanu_hospital.staff.nurse.service;

import org.springframework.stereotype.Service;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.repository.DoctorRepository;
import se2.hanu_hospital.staff.nurse.model.Nurse;
import se2.hanu_hospital.staff.nurse.nurseMapper.NurseDTO;
import se2.hanu_hospital.staff.nurse.nurseMapper.NurseMapper;
import se2.hanu_hospital.staff.nurse.repository.NurseRepository;

import java.io.IOException;
import java.util.List;

@Service
public class NurseService {
    private final NurseRepository nurseRepository;
    private final DoctorRepository doctorRepository;
    private final NurseMapper mapper;

    public NurseService(NurseRepository nurseRepository, DoctorRepository doctorRepository, NurseMapper nurseMapper) {
        this.nurseRepository = nurseRepository;
        this.doctorRepository = doctorRepository;
        this.mapper = nurseMapper;
    }

    public List<Nurse> getAllNurse() {
        return nurseRepository.getAllNurse();
    }

    public void addNurse(Nurse nurse) {
        nurseRepository.save(nurse);
    }

    public void updateById(Long id, NurseDTO nurse) throws IOException {
        if(!nurseRepository.existsById(id)){
            throw new IllegalStateException("There is no nurse with that id!");
        }

        Nurse nurseInDB =  nurseRepository.getNurseById(id);
        mapper.updateNurseFromDto(nurse, nurseInDB);
        nurseRepository.save(nurseInDB);
    }

    public void deleteById(Long id) {
        nurseRepository.deleteById(id);
    }

    public Nurse getById(Long id) {
        Nurse nurse = nurseRepository.getNurseById(id);
        return nurse;
    }

    public void assignDoctor(Long id) {
        Doctor doctor = (Doctor) doctorRepository.getDoctorById(id);
        doctor.setAvailable(false);
        doctorRepository.save(doctor);

        /*Record record = recordRepository.getRecordById(id);
        record.setCurator(doctor);
        recordRepository.save(record);*/

    }

    public List<Doctor> checkAvailableDoctor() {
        return doctorRepository.getDoctorByAvailableIsTrue();
    }

    /*public void addNewPatient() {

    }

    public Record checkRecord() {

    }*/
}
