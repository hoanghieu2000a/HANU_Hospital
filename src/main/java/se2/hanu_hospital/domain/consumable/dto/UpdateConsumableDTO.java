package se2.hanu_hospital.domain.consumable.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UpdateConsumableDTO {
    private Long consumableId;
    private String name;
    private int quantity;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
