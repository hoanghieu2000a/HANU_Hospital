package se2.hanu_hospital.Medicine;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void saveMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public List<Medicine> getMedicine() {
        return medicineRepository.findAll();
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    public double getProfit(Long id){
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return (medicine.getSellPrice() - medicine.getImportPrice());
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
}
