package se2.hanu_hospital.domain.consumable;

import org.springframework.data.jpa.repository.JpaRepository;
import se2.hanu_hospital.domain.consumable.entity.Consumable;

import java.util.List;


public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    // @Query("SELECT * FROM consumable WHERE name = {?} ")
    public List<Consumable> findByName(String consumableName);  
}


