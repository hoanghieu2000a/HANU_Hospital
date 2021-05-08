package se2.hanu_hospital.medical_procedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.equipment.Equipment;
import se2.hanu_hospital.equipment.EquipmentService;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.util.Valid;

import java.util.List;


@Service
public class ProcedureService{

    @Autowired
    private final ProcedureRepository procedureRepository;
    @Autowired
    private final EquipmentService equipmentService;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository, EquipmentService equipmentService){
        this.procedureRepository = procedureRepository;
        this.equipmentService = equipmentService;
    }
    
    public MedicalProcedure addProcedure(MedicalProcedure medicalProcedure) {
        return procedureRepository.save(medicalProcedure);
    }


    public MedicalProcedure updateById(Long id, MedicalProcedurePayload medicalProcedurePayload) {
        if(!procedureRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        MedicalProcedure procedureInDB =  procedureRepository.getProcedureById(id);
        if(Valid.stringValid(medicalProcedurePayload.getName()))
            procedureInDB.setName(medicalProcedurePayload.getName());
        return procedureRepository.save(procedureInDB);
    }

    public void deleteById(Long id) {
        procedureRepository.deleteById(id);     
    }

    public List<MedicalProcedure> findAll() {
        return procedureRepository.findAll();
    }

    public MedicalProcedure getById(Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    public Page<MedicalProcedure> findAll(Pageable pageable) {
        return procedureRepository.findAll(pageable);
    }

    public void addEquipmentToProcedure(Long id, Long equipmentId) {
        if(!procedureRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        MedicalProcedure procedureInDB =  procedureRepository.getProcedureById(id);
        Equipment equipment = equipmentService.getById(equipmentId);

        equipment.getMedicalProcedure().add(procedureInDB);
        procedureInDB.getEquipments().add(equipment);

        equipmentService.updateEquipment(equipment.getId(), equipment);
    }

    public void removeEquipmentFromProcedure(Long id, Long equipmentId) {
        if(!procedureRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        MedicalProcedure procedureInDB =  procedureRepository.getProcedureById(id);
        Equipment equipment = equipmentService.getById(equipmentId);

        equipment.getMedicalProcedure().remove(procedureInDB);
        procedureInDB.getEquipments().add(equipment);

        equipmentService.updateEquipment(equipment.getId(), equipment);
    }

    public void updateMedicalProcedureToRecord(Long medicalProcedureId, MedicalProcedure medicalProcedure) {
        MedicalProcedure medicalProcedureInDB = procedureRepository.findById(medicalProcedureId)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));

        medicalProcedureInDB.setRecord(medicalProcedure.getRecord());
        procedureRepository.save(medicalProcedureInDB);
    }
}

