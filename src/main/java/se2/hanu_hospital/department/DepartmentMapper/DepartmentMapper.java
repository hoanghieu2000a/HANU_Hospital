package se2.hanu_hospital.department.DepartmentMapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import se2.hanu_hospital.department.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoctorFromDto(DepartmentDTO dto, @MappingTarget Department entity);
}
