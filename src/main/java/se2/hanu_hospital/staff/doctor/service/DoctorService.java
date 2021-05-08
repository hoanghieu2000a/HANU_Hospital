package se2.hanu_hospital.staff.doctor.service;

import org.springframework.stereotype.Service;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.prescription.PrescriptionService;
import se2.hanu_hospital.staff.doctor.doctorMapper.DoctorDTO;
import se2.hanu_hospital.staff.doctor.doctorMapper.DoctorMapper;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.repository.DoctorRepository;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PrescriptionService prescriptionService;
    private DoctorMapper mapper;

    public DoctorService(DoctorRepository doctorRepository, PrescriptionService prescriptionService, DoctorMapper mapper) {
        this.doctorRepository = doctorRepository;
        this.prescriptionService = prescriptionService;
        this.mapper = mapper;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void updateById(Long id, DoctorDTO doctor) throws IOException {
        if(!doctorRepository.existsById(id)){
            throw new IllegalStateException("There is no doctor with that id!");
        }

        Doctor doctorInDB = (Doctor) doctorRepository.getDoctorById(id);
        mapper.updateDoctorFromDto(doctor, doctorInDB);
        doctorRepository.save(doctorInDB);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor getById(Long id) {
        return (Doctor) doctorRepository.getDoctorById(id);
    }

//    public void prescribeMedicine(Prescription prescription) throws Exception {
//        prescriptionService.add(prescription);
//    }
//
//    public void updatePrescription(Prescription prescription) throws IOException {
//        prescriptionService.update(prescription);
//    }
//
//    public void deletePrescription(Long id) throws IOException {
//        prescriptionService.delete(id);
//    }

    public List<Doctor> getAvailableDoctors() {
        return doctorRepository.getDoctorByAvailableIsTrue();
    }

    public void viewRecord() {

    }

    public void editRecord() {

    }

}
