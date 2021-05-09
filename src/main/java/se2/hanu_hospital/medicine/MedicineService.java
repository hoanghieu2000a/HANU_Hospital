package se2.hanu_hospital.medicine;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void addMedicine(Medicine medicine) throws IOException {
        if (!medicineValidation(medicine) || isDuplicated(medicine)){
            throw new IOException("Invalid input!");
        }
        medicineRepository.save(medicine);
    }

    public List<Medicine> getMedicine() {
        return medicineRepository.findAll();
    }

    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)){
            throw new IllegalStateException("Order does not exist");
        }
        medicineRepository.deleteById(id);
    }

    public boolean isExpired(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        LocalDate today = LocalDate.now();
        LocalDate expiredDate = medicine.getExpireDate();

        if (today.isBefore(expiredDate)){
            return false;
        } return true;
    }

    public Medicine getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return medicine;
    }

    public Medicine getMedicineByName (String name){
        Medicine medicine = medicineRepository.findByNameContaining(name);
        if (!medicineRepository.existsById(medicine.getId())) {
            throw new IllegalStateException("Order does not exist");
        } return medicine;
    }

    private boolean medicineValidation (Medicine medicine) {
        if (    medicine.getName().length() <= 0 ||
                medicine.getSellPrice() <= 0 ||
                medicine.getImportPrice() <=0 ||
                LocalDate.now().isAfter(medicine.getExpireDate())) {
            return false;
        } return true;
    }

    private boolean isDuplicated(Medicine medicine){
        List<Medicine> medicineList = medicineRepository.findAll();
        for(Medicine medicineX: medicineList){
            if(medicineX.getName().equals(medicine.getName())){
                return true;
            }
        } return false;
    }

    public void updateMedicine(Medicine medicine) throws IOException {
        if(!medicineRepository.existsById(medicine.getId())) {
            throw new IllegalStateException("Invalid medicine");
        }
        if(medicineValidation(medicine)){
        medicineRepository.save(medicine);
        } else {
            throw new IllegalStateException("Invalid medicine");
        }
    }
}
