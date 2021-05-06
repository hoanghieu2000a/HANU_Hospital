package se2.hanu_hospital.domain.consumable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UpdateConsumableDTO {
    
    @NotNull
    private String name;
    @NotNull
    private int quantity;
    @NotNull
    private int priceBought;
    @NotNull
    private int priceSell;
    @NotNull
    private LocalDate importedDate;
    @NotNull
    private Long medicalProcedureId;
    
    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
