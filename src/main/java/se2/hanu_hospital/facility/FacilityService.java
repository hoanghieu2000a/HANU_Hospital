package se2.hanu_hospital.facility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.util.Valid;

import java.util.List;


@Service
public class FacilityService {

    private FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Facility create(Facility facility) {
        return facilityRepository.save(facility);
    }

    public Facility updateById(Long id, FacilityPayload facilityPayload) {
        if(!facilityRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        Facility facilityInDB = facilityRepository.getFacilityById(id);
        if(Valid.stringValid(facilityPayload.getName()))
            facilityInDB.setName(facilityPayload.getName());
        if(Valid.unsignedLongValid(facilityPayload.getPrice()))
            facilityInDB.setPrice(facilityPayload.getPrice());

        return facilityRepository.save(facilityInDB);
    }

    public void deleteById(Long id) {
        facilityRepository.deleteById(id);        
    }

    public List<Facility> findAll() {

        return facilityRepository.findAll();
    }

    public Facility getById(Long id) {
        return facilityRepository.findById(id).orElse(null);
    }

    public Page<Facility> findAll(Pageable pageable) {
        return facilityRepository.findAll(pageable);
    }

    public List<Facility> findByName(String name) {
        return facilityRepository.findByName(name);
    }
    
}