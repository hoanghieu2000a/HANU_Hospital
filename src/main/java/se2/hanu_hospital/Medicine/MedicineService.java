package se2.hanu_hospital.Medicine;

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
        if (medicineValidation(medicine) == false){
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

    public double getProfit(Long id){
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return (medicine.getQuantity()*(medicine.getSellPrice()- medicine.getImportPrice()));
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
        if (medicine.getName().length() <= 0 ||
                medicine.getSellPrice() <= 0 ||
                medicine.getImportPrice() <=0 ||
                medicine.getQuantity() < 0 ||
                LocalDate.now().isAfter(medicine.getExpireDate())) {
            return false;
        } return true;
    }

    public void updateMedicine(Medicine medicine) throws IOException {
        if (medicineValidation(medicine) == false){
            throw new IOException("Invalid input!");
        }
        if (!medicineRepository.existsById(medicine.getId())){
            throw new IllegalStateException("Order does not exist");
        }
        medicineRepository.save(medicine);
    }

    public boolean isExisted(Medicine  medicine){
        if(medicineRepository.existsById(medicine.getId())){
        return true;
        } return false;
    }

    public Double getSellPrice(Long id){
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return medicine.getSellPrice();
    }
}
