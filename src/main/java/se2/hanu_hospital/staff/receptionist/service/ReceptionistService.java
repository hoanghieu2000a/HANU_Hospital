package se2.hanu_hospital.staff.receptionist.service;

import org.springframework.stereotype.Service;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.repository.DoctorRepository;
import se2.hanu_hospital.staff.receptionist.model.Receptionist;
import se2.hanu_hospital.staff.receptionist.receptionistMapper.ReceptionistDTO;
import se2.hanu_hospital.staff.receptionist.receptionistMapper.ReceptionistMapper;
import se2.hanu_hospital.staff.receptionist.repository.ReceptionistRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ReceptionistService {
    private final ReceptionistRepository receptionistRepository;
    private final DoctorRepository doctorRepository;
    private ReceptionistMapper mapper;

    public ReceptionistService(ReceptionistRepository receptionistRepository, DoctorRepository doctorRepository) {
        this.receptionistRepository = receptionistRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Receptionist> getAllReceptionist() {
        return receptionistRepository.getAllReceptionist();
    }

    public void addReceptionist(Receptionist receptionist) {
        receptionistRepository.save(receptionist);
    }

    public void updateById(Long id, ReceptionistDTO receptionist) throws IOException {
        if(!receptionistRepository.existsById(id)){
            throw new IllegalStateException("There is no receptionist with that id!");
        }

        Receptionist receptionistInDB = (Receptionist) receptionistRepository.getReceptionistById(id);
        mapper.updateReceptionistFromDto(receptionist, receptionistInDB);
        receptionistRepository.save(receptionistInDB);
    }

    public void deleteById(Long id) {
        receptionistRepository.deleteById(id);
    }

    public Receptionist getById(Long id) {
        return (Receptionist) receptionistRepository.getReceptionistById(id);
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
