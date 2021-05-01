package se2.hanu_hospital.Prescription;

import org.springframework.stereotype.Service;
import se2.hanu_hospital.Medicine.Medicine;
import se2.hanu_hospital.Medicine.MedicineRepository;
import se2.hanu_hospital.Medicine.MedicineService;

import java.io.IOException;
import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private MedicineService medicineService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicineService medicineService ) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicineService = medicineService;
    }

    public void add(Prescription prescription) throws Exception {
        Medicine medicine = medicineService.getMedicineByName(prescription.getName());
        try{
        if(medicineService.isExisted(medicine)){
            if( prescriptionValidate(prescription)) {
                prescriptionRepository.save(prescription);

                List<Prescription> prescriptionList = prescriptionRepository.findAll();

                updateMedicineQuantity(medicine, prescriptionList);
            } else {
                throw new Exception("Invalid input");
            }
            }
        } catch (Exception e){
            throw new IllegalStateException("Medicine does not exist");
        }
    }

    public List<Prescription> getAll(){
        return prescriptionRepository.findAll();
    }

    public void delete (Long id){
        if(!prescriptionRepository.existsById(id)){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescriptionRepository.deleteById(id);
    }

    public void update (Prescription prescription){
        if(!prescriptionValidate(prescription)) {
            throw new IllegalStateException("Invalid input");
        }
        if(!prescriptionRepository.existsById(prescription.getId())){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescriptionRepository.save(prescription);
    }

    public Prescription getById(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return prescription;
    }

    private boolean prescriptionValidate(Prescription prescription){
        if( prescription.getName().length() <=0 ||
            prescription.getDosage()<= 0||
            prescription.getCostPerDose() <= 0||
            prescription.getRecordId()<= 0||
            prescription.getTotal()<= 0){
            return false;
        } return true;
    }

    private void updateMedicineQuantity(Medicine medicine, List<Prescription> prescriptionList) throws IOException {
        for (Prescription prescription: prescriptionList){
            medicine.setQuantity(medicine.getQuantity()+prescription.getDosage());
        }
        medicineService.updateMedicine(medicine);
    }
}
