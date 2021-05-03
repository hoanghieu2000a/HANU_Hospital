package se2.hanu_hospital.Medicine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByNameContaining(String name);

}
