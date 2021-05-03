package se2.hanu_hospital.facility.dto;

import java.time.LocalDateTime;

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
    private Long facilityId;
    private String code;
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
