package se2.hanu_hospital.staff.nurse.nurseMapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import se2.hanu_hospital.staff.nurse.model.Nurse;

@Mapper(componentModel = "spring")
public interface NurseMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNurseFromDto(NurseDTO dto, @MappingTarget Nurse entity);
}