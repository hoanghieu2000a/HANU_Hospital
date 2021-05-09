package se2.hanu_hospital.medical_procedure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import se2.hanu_hospital.billline.ServiceBillLine;
import se2.hanu_hospital.equipment.Equipment;
import se2.hanu_hospital.record.Record;


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

    @ManyToMany(mappedBy = "medicalProcedure", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Equipment> equipments;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "record")
    @JsonIgnore
    private Record record;

    @OneToOne(mappedBy = "medicalProcedure", cascade=CascadeType.ALL)
    @JsonIgnore
    private ServiceBillLine billLine;

    public MedicalProcedure(Long id, String name, List<Equipment> equipments) {
        this.id = id;
        this.name = name;
        this.equipments = equipments;
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

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public ServiceBillLine getBillLine() {
        return billLine;
    }

    public void setBillLine(ServiceBillLine billLine) {
        this.billLine = billLine;
    }
}
