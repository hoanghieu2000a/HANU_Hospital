package se2.hanu_hospital.medical_procedure.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

public class UpdateProcedureDTO {
    private Long procedureId;
    private String patientName;
    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
