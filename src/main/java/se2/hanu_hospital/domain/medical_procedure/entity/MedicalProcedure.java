package se2.hanu_hospital.domain.medical_procedure.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se2.hanu_hospital.domain.consumable.entity.Consumable;
import se2.hanu_hospital.domain.facility.entity.Facility;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Facility> facilities;
    private List<Consumable> consumables;

}
