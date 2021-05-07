package se2.hanu_hospital.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.patient.PatientService;
import se2.hanu_hospital.prescription.PrescriptionService;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.service.DoctorService;
import se2.hanu_hospital.util.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final PrescriptionService prescriptionService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Autowired
    public RecordService(RecordRepository recordRepository, PrescriptionService prescriptionService, PatientService patientService, DoctorService doctorService) {
        this.recordRepository = recordRepository;
        this.prescriptionService = prescriptionService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public List<Record> getAllRecord(){
        return recordRepository.findAll();
    }

    public void addRecord(RecordPayload recordPayLoad){
        Record record = new Record();
        record.setDescription(recordPayLoad.getDescription());
        record.setDiagnosis(recordPayLoad.getDiagnosis());
        Doctor doctor = doctorService.getById(recordPayLoad.getDoctorId());
        record.setDoctor(doctor);
        Patient patient = patientService.getById(recordPayLoad.getId());
        record.setPatient(patient);

        recordRepository.save(record);
    }

    public void deleteRecord(Long id) throws IOException {
        if (!recordRepository.existsById(id)){
            throw new IllegalStateException("Record does not exist");
        }
        prescriptionService.deleteAllPresOfRecord(id);
        recordRepository.deleteById(id);
    }

    public void updateRecord(RecordPayload recordPayLoad){
        if (!recordRepository.existsById(recordPayLoad.getId())){
            throw new IllegalStateException("Record does not exist");
        }

        Record record = recordRepository.getRecordById(recordPayLoad.getId());
        if(Valid.stringValid(recordPayLoad.getDescription()))
            record.setDescription(recordPayLoad.getDescription());
        if(Valid.stringValid(recordPayLoad.getDiagnosis()))
            record.setDiagnosis(recordPayLoad.getDiagnosis());
        if(Valid.unsignedLongValid(recordPayLoad.getDoctorId())){
            Doctor doctor = doctorService.getById(recordPayLoad.getDoctorId());
            record.setDoctor(doctor);
        }
        if(Valid.unsignedLongValid(recordPayLoad.getPatientId())){
            Patient patient = patientService.getById(recordPayLoad.getId());
            record.setPatient(patient);
        }

        recordRepository.save(record);
    }

    public Record getRecordById(Long id){
        return recordRepository.findById(id).orElseThrow(() -> new IllegalStateException("Record does not exist!"));
    }

    public Set<Record> getRecordByPatientId(Long id){
        Patient patient = patientService.getById(id);
        if(patient == null)
            return null;
        return patient.getRecords();
    }

    public Set<Record> getRecordByDoctorId(Long id){
        Doctor doctor = doctorService.getById(id);
        if(doctor == null)
            return null;
        return doctor.getRecords();
    }


}
