package se2.hanu_hospital.medical_procedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.procedureMapper.ProcedureDTO;
import se2.hanu_hospital.medical_procedure.procedureMapper.ProcedureMapper;
import se2.hanu_hospital.util.CRUDService;

import java.util.List;


@Service
public class ProcedureService{

    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private ProcedureMapper mapper;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository){
        this.procedureRepository = procedureRepository;
        this.mapper = mapper;
    }
    
    public MedicalProcedure addProcedure(MedicalProcedure medicalProcedure) {
        return procedureRepository.save(medicalProcedure);
    }


    public MedicalProcedure updateById(Long id, ProcedureDTO procedureDTO) {
        if(!procedureRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        MedicalProcedure procedureInDB =  procedureRepository.getProcedureById(id);
        mapper.updateProcedureFromDto(procedureDTO, procedureInDB);
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
}

