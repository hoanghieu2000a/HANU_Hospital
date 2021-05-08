package se2.hanu_hospital.consumable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    // @Query("SELECT * FROM consumable WHERE name = {?} ")
    public List<Consumable> findByName(String consumableName);  
}


