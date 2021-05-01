package se2.hanu_hospital.domain.consumable;


import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

import se2.hanu_hospital.domain.consumable.dto.CreateConsumableDTO;
import se2.hanu_hospital.domain.consumable.dto.UpdateConsumableDTO;
import se2.hanu_hospital.domain.consumable.entity.Consumable;
import se2.hanu_hospital.util.CRUDService;

public interface ConsumableService extends CRUDService<Consumable, Long, CreateConsumableDTO, UpdateConsumableDTO> {
    List<Consumable> findAllByName(String consumableName);
    
}
