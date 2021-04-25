package se2.hanu_hospital.Prescription;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Column(columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long recordId;

    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "INT(4) UNSIGNED")
    private int quantity;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "INT(4) UNSIGNED")
    private int dosage;

    @Column(precision = 8, scale = 2)
    private Double costPerDose;
    @Column(precision = 8, scale = 2)
    private Double total;

    public Prescription(Long id, Long recordId, String name, int quantity, LocalDate startDate, LocalDate endDate, int dosage, Double costPerDose, Double total) {
        this.id = id;
        this.recordId = recordId;
        this.name = name;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dosage = dosage;
        this.costPerDose = costPerDose;
        this.total = total;
    }

    public Prescription() {
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", recordId=" + recordId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dosage=" + dosage +
                ", costPerDose=" + costPerDose +
                ", total=" + total +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Double getCostPerDose() {
        return costPerDose;
    }

    public void setCostPerDose(Double costPerDose) {
        this.costPerDose = costPerDose;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
