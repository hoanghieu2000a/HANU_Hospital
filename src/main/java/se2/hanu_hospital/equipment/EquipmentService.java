package se2.hanu_hospital.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.util.Valid;

import java.util.List;


@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment create(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateById(Long id, EquipmentPayload equipmentPayload) {
        if(!equipmentRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        Equipment equipmentInDB = equipmentRepository.getFacilityById(id);
        if(Valid.stringValid(equipmentPayload.getName()))
            equipmentInDB.setName(equipmentPayload.getName());
        if(equipmentPayload.getPrice() > 0)
            equipmentInDB.setPrice(equipmentPayload.getPrice());
        if(equipmentPayload.getQuantity() > 0){
            equipmentInDB.setQuantity(equipmentPayload.getQuantity());
        }

        return equipmentRepository.save(equipmentInDB);
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> findAll() {

        return equipmentRepository.findAll();
    }

    public Equipment getById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public Page<Equipment> findAll(Pageable pageable) {
        return equipmentRepository.findAll(pageable);
    }

    public List<Equipment> findByName(String name) {
        return equipmentRepository.findByName(name);
    }

    public void updateEquipment(Long id, Equipment equipment){
        Equipment equipmentInDB = equipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));

        equipmentInDB.setMedicalProcedure(equipment.getMedicalProcedure());
        equipmentRepository.save(equipmentInDB);
    }
}