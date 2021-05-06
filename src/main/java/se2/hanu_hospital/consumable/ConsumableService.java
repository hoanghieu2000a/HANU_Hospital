package se2.hanu_hospital.consumable;



import se2.hanu_hospital.consumable.dto.CreateConsumableDTO;
import se2.hanu_hospital.consumable.dto.UpdateConsumableDTO;
import se2.hanu_hospital.consumable.entity.Consumable;
import se2.hanu_hospital.util.CRUDService;

import java.util.List;

// import org.springframework.data.Page;
// import org.springframework.data.Pageable;


public interface ConsumableService extends CRUDService<Consumable, Long, CreateConsumableDTO, UpdateConsumableDTO> {
    List<Consumable> findAllByName(String consumableName);
    
}
