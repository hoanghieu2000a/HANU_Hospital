package se2.hanu_hospital.facility;

import java.util.List;

import se2.hanu_hospital.facility.dto.CreateFacilityDTO;
import se2.hanu_hospital.facility.dto.UpdateFacilityDTO;
import se2.hanu_hospital.facility.entity.Facility;
import se2.hanu_hospital.util.CRUDService;

public interface FacilityService extends CRUDService<Facility, Long, CreateFacilityDTO, UpdateFacilityDTO>{
    List<Facility> findByCode(String code); 
}
