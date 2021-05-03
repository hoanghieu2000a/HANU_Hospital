package se2.hanu_hospital.medicine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByNameContaining(String name);

}
