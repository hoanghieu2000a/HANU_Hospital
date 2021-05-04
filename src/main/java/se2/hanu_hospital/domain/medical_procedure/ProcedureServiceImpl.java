package se2.hanu_hospital.domain.medical_procedure;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import se2.hanu_hospital.domain.medical_procedure.dto.CreateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.dto.UpdateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.entity.MedicalProcedure;

@Service
public class ProcedureServiceImpl implements ProcedureService{

    private ProcedureRepository procedureRepository;
    private ModelMapper modelMapper;

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

