package se2.hanu_hospital.domain.medical_procedure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se2.hanu_hospital.domain.consumable.entity.Consumable;
import se2.hanu_hospital.domain.facility.entity.Facility;
import se2.hanu_hospital.domain.patient.entity.Patient;

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
