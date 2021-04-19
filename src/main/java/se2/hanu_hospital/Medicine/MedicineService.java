package se2.hanu_hospital.Medicine;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void addNewMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @Transactional
    public void updateMedicine(Long id, Medicine medicine) {
        medicine.setId(id);
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
}
