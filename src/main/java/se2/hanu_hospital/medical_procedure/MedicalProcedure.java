package se2.hanu_hospital.medical_procedure;

import lombok.*;
import se2.hanu_hospital.consumable.Consumable;
import se2.hanu_hospital.facility.Facility;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Facility> facilities;

    @OneToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Consumable> consumables;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public MedicalProcedure(Long id, String name, List<Facility> facilities, List<Consumable> consumables) {
        this.id = id;
        this.name = name;
        this.facilities = facilities;
        this.consumables = consumables;
    }

    public MedicalProcedure(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MedicalProcedure() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }
}
