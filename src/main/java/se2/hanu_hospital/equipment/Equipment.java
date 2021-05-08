package se2.hanu_hospital.equipment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "equipment_procedure",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_procedure_id"))
    @JsonIgnore
    private Set<MedicalProcedure> medicalProcedure = new HashSet<>();

    public Equipment(Long id, String name, Double price, Integer quantity, Set<MedicalProcedure> medicalProcedure) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.medicalProcedure = medicalProcedure;
    }

    public Equipment(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Equipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<MedicalProcedure> getMedicalProcedure() {
        return medicalProcedure;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMedicalProcedure(Set<MedicalProcedure> medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }
}