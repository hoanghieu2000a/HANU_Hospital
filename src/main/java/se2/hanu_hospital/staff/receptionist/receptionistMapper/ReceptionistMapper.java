package se2.hanu_hospital.staff.receptionist.receptionistMapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import se2.hanu_hospital.staff.receptionist.model.Receptionist;

@Mapper(componentModel = "spring")
public interface ReceptionistMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReceptionistFromDto(ReceptionistDTO dto, @MappingTarget Receptionist entity);
}