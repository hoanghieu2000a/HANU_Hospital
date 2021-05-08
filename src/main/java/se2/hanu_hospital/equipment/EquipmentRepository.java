package se2.hanu_hospital.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
    List<Equipment> findByName(String name);

    Equipment getFacilityById(Long id);
}
