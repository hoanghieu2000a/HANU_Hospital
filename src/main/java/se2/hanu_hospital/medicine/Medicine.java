package se2.hanu_hospital.medicine;

import se2.hanu_hospital.prescription.Prescription;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Size(max = 50)
    private String name;

    private LocalDate expireDate;

    @Column(precision = 8, scale = 2)
    private Double sellPrice;

    @Column(precision = 8, scale = 2)
    private Double importPrice;

    @Column(columnDefinition = "INT(4) UNSIGNED")
    private int quantity;



    public Medicine(Long id, String name, LocalDate expireDate, Double sellPrice, Double importPrice, int quantity) {
        this.id = id;
        this.expireDate = expireDate;
        this.name = name;
        this.sellPrice = sellPrice;
        this.importPrice = importPrice;
        this.quantity = quantity;
    }
    public Medicine() { }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) { this.sellPrice = sellPrice; }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }
}

