package se2.hanu_hospital.domain.facility;

import java.util.List;

import se2.hanu_hospital.domain.facility.dto.CreateFacilityDTO;
import se2.hanu_hospital.domain.facility.dto.UpdateFacilityDTO;
import se2.hanu_hospital.domain.facility.entity.Facility;
import se2.hanu_hospital.util.CRUDService;

public interface FacilityService extends CRUDService<Facility, Long, CreateFacilityDTO, UpdateFacilityDTO>{
    List<Facility> findByCode(String code); 
}
