package se2.hanu_hospital.consumable.entity;

import lombok.*;
import se2.hanu_hospital.medical_procedure.entity.MedicalProcedure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private String name;
    private int quantity;
    private int priceBought;
    private int priceSell;
    private LocalDate importedDate;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "medical_procedure_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MedicalProcedure medicalProcedure; 
}
