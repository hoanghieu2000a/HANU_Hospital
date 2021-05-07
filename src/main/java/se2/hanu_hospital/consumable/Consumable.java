package se2.hanu_hospital.consumable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private int quantity;
    @NotNull
    private int priceBought;
    @NotNull
    private int priceSell;

    @ManyToOne
    @JoinColumn(name = "medical_procedure_id")
    @JsonIgnore
    private MedicalProcedure medicalProcedure;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
