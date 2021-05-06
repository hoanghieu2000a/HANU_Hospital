package se2.hanu_hospital.medical_procedure.entity;

import lombok.*;
import se2.hanu_hospital.consumable.entity.Consumable;
import se2.hanu_hospital.facility.entity.Facility;
import se2.hanu_hospital.patient.entity.Patient;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String medicalProcedureCode;

    @OneToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Facility> facilities;

    @OneToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Consumable> consumables;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;




}
