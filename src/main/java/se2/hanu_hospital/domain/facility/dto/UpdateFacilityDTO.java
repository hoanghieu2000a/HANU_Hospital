package se2.hanu_hospital.domain.facility.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UpdateFacilityDTO {
    @NotNull
    private String code;
    @NotNull
    private Boolean status;
    @NotNull
    private int price;

    private Long medicalProcedureId;
    
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
