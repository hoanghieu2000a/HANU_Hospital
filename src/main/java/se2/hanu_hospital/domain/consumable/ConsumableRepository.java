package se2.hanu_hospital.domain.consumable;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import se2.hanu_hospital.domain.consumable.entity.Consumable;



public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    // @Query("SELECT * FROM consumable WHERE name = {?} ")
    public List<Consumable> findByName(String consumableName);  
}


