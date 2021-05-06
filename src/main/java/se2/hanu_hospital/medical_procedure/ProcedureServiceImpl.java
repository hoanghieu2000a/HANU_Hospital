package se2.hanu_hospital.medical_procedure;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.medical_procedure.dto.CreateProcedureDTO;
import se2.hanu_hospital.medical_procedure.dto.UpdateProcedureDTO;
import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;

import java.util.List;


@Service
public class ProcedureServiceImpl implements ProcedureService{

    private ProcedureRepository procedureRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, ModelMapper modelMapper){
        this.procedureRepository = procedureRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public MedicalProcedure create(CreateProcedureDTO createProcedureDTO) {
        MedicalProcedure pro = modelMapper.map(createProcedureDTO, MedicalProcedure.class);
        return procedureRepository.save(pro);
    }

    @Override
    public MedicalProcedure updateById(Long id, UpdateProcedureDTO updateProcedureDTO) {
        MedicalProcedure pro = procedureRepository.findById(id).orElse(null);
        modelMapper.map(updateProcedureDTO, pro);
        return procedureRepository.save(pro);
    }

    @Override
    public void deleteById(Long id) {
        procedureRepository.deleteById(id);     
    }

    @Override
    public List<MedicalProcedure> findAll() {
        return procedureRepository.findAll();
    }

    @Override
    public MedicalProcedure getById(Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    @Override
    public Page<MedicalProcedure> findAll(Pageable pageable) {
        return procedureRepository.findAll(pageable);
    }

    @Override
    public List<MedicalProcedure> findByPatientName(String patientName) {
        return procedureRepository.findByPatientName(patientName);
    }

}

