package se2.hanu_hospital.medical_procedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.util.Valid;

import java.util.List;


@Service
public class ProcedureService{

    @Autowired
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository){
        this.procedureRepository = procedureRepository;
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
}

