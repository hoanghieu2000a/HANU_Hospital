package se2.hanu_hospital.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.prescription.PrescriptionService;

import java.io.IOException;
import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final PrescriptionService prescriptionService;

    public RecordService(RecordRepository recordRepository, PrescriptionService prescriptionService) {
        this.recordRepository = recordRepository;
        this.prescriptionService = prescriptionService;
    }

    public List<Record> getAllRecord(){
        return recordRepository.findAll();
    }

    public void addRecord(Record record){
        try{
            if(validateRecord(record)){
                recordRepository.save(record);
            }
        } catch (Exception e){
            throw new IllegalStateException("Invalid input");
        }
    }

    public void deleteRecord(Long id) throws IOException {
        if (!recordRepository.existsById(id)){
            throw new IllegalStateException("Record does not exist");
        }
        Record record =getRecordById(id);

        prescriptionService.deleteAllPresByRecordId(record.getId());
        recordRepository.delete(record);

    }

    public void updateRecord(Record record){
        if(!validateRecord(record)){
            throw new IllegalStateException("Invallid input");
        }
        if (!recordRepository.existsById(record.getId())){
            throw new IllegalStateException("Record does not exist");
        }

        recordRepository.save(record);
    }

    public void changeStatus(Long id){
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Record does not exist!"));
        if(record.getStatus()==RecordStatus.CONFIRM){
            record.setStatus(RecordStatus.DISCHARGED);
            recordRepository.save(record);
        }
    }


    public Record getRecordById(Long id){
        return recordRepository.findById(id).orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
    }

    public List<Record> getRecordByPatientId(Long id){
        return recordRepository.findRecordByPatientId(id);
    }

    public List<Record> getRecordByDoctorId(Long id){
        return recordRepository.findRecordByDoctorId(id);
    }

    private boolean validateRecord(Record record){
        if( record.getDoctorId() <= 0||
            record.getPatientId() <= 0){
            return false;
        } return true;
    }
}
