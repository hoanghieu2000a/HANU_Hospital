package se2.hanu_hospital.domain.facility.dto;

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
    private Long id;
    private String code;
    @JsonIgnore
    private LocalDateTime  updatedAt = LocalDateTime.now();
    
}
