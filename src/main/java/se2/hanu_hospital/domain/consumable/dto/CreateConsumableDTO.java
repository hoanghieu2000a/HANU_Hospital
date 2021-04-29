package se2.hanu_hospital.domain.consumable.dto;

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
public class CreateConsumableDTO {
    private long consumableId;
    private String name;
    private int quantity;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;
    
}
