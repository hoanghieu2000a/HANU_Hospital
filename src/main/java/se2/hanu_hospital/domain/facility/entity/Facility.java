package se2.hanu_hospital.domain.facility.entity;


import lombok.*;
import se2.hanu_hospital.domain.medical_procedure.entity.MedicalProcedure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private Boolean status;
    @NotNull
    private int price;
    
    @ManyToOne
    @JoinColumn(name = "medical_procedure_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MedicalProcedure medicalProcedure;  
}