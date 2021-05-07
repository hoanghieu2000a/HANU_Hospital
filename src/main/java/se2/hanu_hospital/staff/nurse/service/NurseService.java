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
    private final NurseRepository receptionistRepository;
    private final DoctorRepository doctorRepository;
    private NurseMapper mapper;

    public NurseService(NurseRepository receptionistRepository, DoctorRepository doctorRepository) {
        this.receptionistRepository = receptionistRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Nurse> getAllReceptionist() {
        return receptionistRepository.getAllReceptionist();
    }

    public void addReceptionist(Nurse nurse) {
        receptionistRepository.save(nurse);
    }

    public void updateById(Long id, NurseDTO receptionist) throws IOException {
        if(!receptionistRepository.existsById(id)){
            throw new IllegalStateException("There is no receptionist with that id!");
        }

        Nurse nurseInDB = (Nurse) receptionistRepository.getReceptionistById(id);
        mapper.updateReceptionistFromDto(receptionist, nurseInDB);
        receptionistRepository.save(nurseInDB);
    }

    public void deleteById(Long id) {
        receptionistRepository.deleteById(id);
    }

    public Nurse getById(Long id) {
        return (Nurse) receptionistRepository.getReceptionistById(id);
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
