package se2.hanu_hospital.medical_procedure.procedureMapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProcedureFromDto(ProcedureDTO dto, @MappingTarget MedicalProcedure entity);
}
