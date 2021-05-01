package se2.hanu_hospital.domain.consumable;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import se2.hanu_hospital.domain.consumable.dto.CreateConsumableDTO;
import se2.hanu_hospital.domain.consumable.dto.UpdateConsumableDTO;
import se2.hanu_hospital.domain.consumable.entity.Consumable;

@Service
public class ConsumableServiceImp  implements ConsumableService{

    private final ConsumableRepository consumableRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ConsumableServiceImp(ConsumableRepository consumableRepository, ModelMapper modelMapper){
        this.consumableRepository = consumableRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Consumable create(CreateConsumableDTO createConsumableDTO) {
        Consumable consumable = modelMapper.map(createConsumableDTO, Consumable.class);
        return consumableRepository.save(consumable);
    }

    @Override
    public Consumable updateById(Long id, UpdateConsumableDTO updateConsumableDTO) {
        Consumable consumable = consumableRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateConsumableDTO, consumable);
        return consumableRepository.save(consumable);
    }

    @Override
    public void deleteById(Long id) {
        consumableRepository.deleteById(id);        
    }

    @Override
    public List<Consumable> findAll() {
        return consumableRepository.findAll();
    }

    @Override
    public Consumable getById(Long id) {
        return consumableRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Consumable> findAll(Pageable pageable) {
        return consumableRepository.findAll(pageable);
    }

    @Override
    public List<Consumable> findAllByName(String consumableName) {
        
        return consumableRepository.findByName(consumableName);
    }






    
}
