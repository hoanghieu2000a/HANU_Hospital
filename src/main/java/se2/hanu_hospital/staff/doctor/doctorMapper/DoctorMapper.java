package se2.hanu_hospital.staff.doctor.doctorMapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import se2.hanu_hospital.staff.doctor.model.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoctorFromDto(DoctorDTO dto, @MappingTarget Doctor entity);
}
