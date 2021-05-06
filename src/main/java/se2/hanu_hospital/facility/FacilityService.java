package se2.hanu_hospital.domain.facility;


import se2.hanu_hospital.domain.facility.dto.CreateFacilityDTO;
import se2.hanu_hospital.domain.facility.dto.UpdateFacilityDTO;
import se2.hanu_hospital.domain.facility.entity.Facility;
import se2.hanu_hospital.util.CRUDService;

import java.util.List;


public interface FacilityService extends CRUDService<Facility, Long, CreateFacilityDTO, UpdateFacilityDTO> {
    List<Facility> findByCode(String code); 
}
