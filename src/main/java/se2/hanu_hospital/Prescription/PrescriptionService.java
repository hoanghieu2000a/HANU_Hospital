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
                updateMedicineQuantity(medicine.getName());
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

    public void delete (Long id) throws IOException {
        if(!prescriptionRepository.existsById(id)){
            throw new IllegalStateException ("Prescription does not Exist");
        }
        Prescription prescription = getById(id);
        String name = prescription.getName();

        prescriptionRepository.deleteById(id);

        updateMedicineQuantity(name);
    }

    public void update (Prescription prescription) throws IOException {
        if(!prescriptionValidate(prescription)) {
            throw new IllegalStateException("Invalid input");
        }
        if(!prescriptionRepository.existsById(prescription.getId())){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescriptionRepository.save(prescription);

        String name = prescription.getName();

        updateMedicineQuantity(name);
    }

    public Prescription getById(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));
        return prescription;
    }

    public List<Prescription> getAllByRecordId(Long recordId){
        List<Prescription> prescriptionList = prescriptionRepository.findPrescriptionByRecordId(recordId);
        return prescriptionList;
    }

    private boolean prescriptionValidate(Prescription prescription){
        if( prescription.getName().length() <=0 ||
            prescription.getDosage()<= 0||
            prescription.getRecordId()<= 0
            ){
            return false;
        } return true;
    }

    private void updateMedicineQuantity(String medicineName) throws IOException {
        List<Prescription> prescriptionList = prescriptionRepository.findAllByNameContaining(medicineName);
        Medicine medicine = medicineService.getMedicineByName(medicineName);

        int currQty = 0;
        if (prescriptionList.size() == 0) {
            medicine.setQuantity(0);
            medicineService.updateMedicine(medicine);
        } else {
            for (Prescription prescription : prescriptionList) {
                currQty += prescription.getDosage();
            }
            medicine.setQuantity(currQty);
            medicineService.updateMedicine(medicine);
        }
    }

    public int getQuantity(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Prescription does not exist!"));
        return prescription.getDosage();
    }
}
