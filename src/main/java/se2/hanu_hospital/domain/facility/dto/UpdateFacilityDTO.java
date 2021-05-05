package se2.hanu_hospital.domain.facility.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
