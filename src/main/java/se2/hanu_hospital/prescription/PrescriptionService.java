package se2.hanu_hospital.prescription;

import org.springframework.stereotype.Service;
import se2.hanu_hospital.medicine.Medicine;
import se2.hanu_hospital.medicine.MedicineService;
import se2.hanu_hospital.record.RecordRepository;
import se2.hanu_hospital.record.Record;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineService medicineService;
    private final RecordRepository recordRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicineService medicineService, RecordRepository recordRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicineService = medicineService;
        this.recordRepository = recordRepository;
    }

    public void add(Prescription prescription, Long recordId, Long medicineId) throws Exception {
        if(!prescriptionValidate(prescription)){
            throw new IllegalStateException("invalid");
        }
        prescription.setMedicine(medicineService.getMedicineById(medicineId));
        prescription.setRecord(recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalStateException("Record does not exist!")));
        prescriptionRepository.save(prescription);
        updateMedicineQty(medicineId);
    }

    public List<Prescription> getAll(){
        return prescriptionRepository.findAll();
    }

    public void delete (Long id) throws IOException {
        if(!prescriptionRepository.existsById(id)){
            throw new IllegalStateException ("Prescription does not Exist");
        }
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));
        Long medicineId = prescription.getMedicine().getId();
        prescriptionRepository.deleteById(id);
        updateMedicineQty(medicineId);
    }

    public void deleteAllPresOfRecord(Long id){
        Set<Prescription> prescriptionList = getAllByRecordId(id);
        for(Prescription prescriptionX : prescriptionList){
                prescriptionRepository.delete(prescriptionX);

        }
    }

    public void update (Prescription prescription, Long recordId, Long medicineId) throws IOException {
        if(!prescriptionValidate(prescription)) {
            throw new IllegalStateException("Invalid input");
        }
        if(!prescriptionRepository.existsById(prescription.getId())){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescription.setRecord(recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalStateException("Record does not exist!")));
        prescription.setMedicine(medicineService.getMedicineById(medicineId));
        prescriptionRepository.save(prescription);
        updateMedicineQty(medicineId);
    }

    public Prescription getById(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));
        return prescription;
    }

    public Set<Prescription> getAllByRecordId(Long recordId){
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalStateException("Record does not exist!"));
        return record.getPrescriptionMedicine();
    }

    private boolean prescriptionValidate(Prescription prescription){
        if(prescription.getDosage()<= 0){
            return false;
        } return true;
    }

    public void updateMedicineQty(Long medicineId) throws IOException {
        int currQty = 0;
        Medicine medicine = medicineService.getMedicineById(medicineId);
        List<Prescription> prescriptionList = getAll();
        for(Prescription prescriptionX: prescriptionList){
            if(prescriptionX.getMedicine().equals(medicine)){
                currQty += prescriptionX.getDosage();
            }
        }
        medicine.setQuantity(currQty);
        medicineService.updateMedicine(medicine);
    }

    public int getQuantity(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));
        return prescription.getDosage();
    }

    public void updatePrescriptionToRecord(Long prescriptionId, Prescription prescription) {
        Prescription prescriptionInDB = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));

        prescriptionInDB.setRecord(prescription.getRecord());
        prescriptionRepository.save(prescriptionInDB);
    }
}
