package se2.hanu_hospital.facility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.facility.dto.CreateFacilityDTO;
import se2.hanu_hospital.facility.dto.UpdateFacilityDTO;
import se2.hanu_hospital.facility.entity.Facility;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class FacilityServiceImp implements FacilityService{

    private FacilityRepository facilityRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FacilityServiceImp(FacilityRepository facilityRepository, ModelMapper modelMapper) {
        this.facilityRepository = facilityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Facility create(CreateFacilityDTO createFacilityDTO) {
        Facility facility = modelMapper.map(createFacilityDTO, Facility.class);
        return facilityRepository.save(facility);
    }

    @Override
    public Facility updateById(Long id, UpdateFacilityDTO updateFacilityDTO) {
        Facility facility = facilityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateFacilityDTO, facility);
        return facilityRepository.save(facility);
    }

    @Override
    public void deleteById(Long id) {
        facilityRepository.deleteById(id);        
    }

    @Override
    public List<Facility> findAll() {

        return facilityRepository.findAll();
    }

    @Override
    public Facility getById(Long id) {
        return facilityRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Facility> findAll(Pageable pageable) {
        return facilityRepository.findAll(pageable);
    }

    @Override
    public List<Facility> findByCode(String code) {
        return facilityRepository.findByCode(code);
    }
    
}