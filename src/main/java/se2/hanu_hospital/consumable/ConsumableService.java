package se2.hanu_hospital.consumable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class ConsumableService{

    @Autowired
    private ConsumableRepository consumableRepository;


    public Consumable create(Consumable consumable) {
        return consumableRepository.save(consumable);
    }

    public Consumable updateById(Long id, ConsumablePayload consumablePayload) {
        Consumable consumable = consumableRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return consumableRepository.save(consumable);
    }

    public void deleteById(Long id) {
        consumableRepository.deleteById(id);        
    }

    public List<Consumable> findAll() {
        return consumableRepository.findAll();
    }

    public Consumable getById(Long id) {
        return consumableRepository.findById(id).orElse(null);
    }

    public Page<Consumable> findAll(Pageable pageable) {
        return consumableRepository.findAll(pageable);
    }

    public List<Consumable> findAllByName(String consumableName) {
        
        return consumableRepository.findByName(consumableName);
    }  
}
