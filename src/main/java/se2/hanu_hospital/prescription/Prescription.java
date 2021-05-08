package se2.hanu_hospital.prescription;


import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.billline.MedicalBillLine;
import se2.hanu_hospital.medicine.Medicine;
import se2.hanu_hospital.record.Record;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

//    @Column(columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
//    private Long recordId;

//    @Column(length = 50)
//    private String name;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "INT(4) UNSIGNED")
    private int dosage;

    @ManyToOne
    @JoinColumn(name = "record_id")
    @JsonIgnore
    private Record record;

    @OneToOne(mappedBy = "prescription")
    private MedicalBillLine billLine;

    public Prescription(Long id, Record record, Medicine medicine, LocalDate startDate, LocalDate endDate, int dosage) {
        this.id = id;
        this.record = record;
        this.medicine = medicine;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dosage = dosage;
    }

    public Prescription() {
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", record=" + record +
                ", medicine='" + medicine + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dosage=" + dosage +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }


}
