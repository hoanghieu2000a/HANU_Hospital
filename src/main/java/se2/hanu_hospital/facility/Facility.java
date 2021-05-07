package se2.hanu_hospital.facility;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long price;
    
    @ManyToOne
    @JoinColumn(name = "medical_procedure_id")
    @JsonIgnore
    private MedicalProcedure medicalProcedure;

    public Facility(Long id, String name, Long price, MedicalProcedure medicalProcedure) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.medicalProcedure = medicalProcedure;
    }

    public Facility(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Facility() {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public MedicalProcedure getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(MedicalProcedure medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }
}