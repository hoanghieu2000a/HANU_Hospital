package se2.hanu_hospital.consumable.entity;

import lombok.*;
import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "medical_procedure_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MedicalProcedure medicalProcedure;
}
