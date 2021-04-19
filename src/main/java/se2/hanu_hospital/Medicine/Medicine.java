package se2.hanu_hospital.Medicine;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime importDate;
    private LocalDate expireDate;

    @Column(precision = 8, scale = 2)
    private Double sellPrice;

    @Column(precision = 8, scale = 2)
    private Double importPrice;

    public Medicine(String name, LocalDateTime importDate, LocalDate expireDate, Double sellPrice, Double importPrice) {
        this.name = name;
        this.importDate = importDate;
        this.expireDate = expireDate;
        this.sellPrice = sellPrice;
        this.importPrice = importPrice;
    }

    public Medicine() {
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

    public LocalDateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDateTime importDate) {
        this.importDate = importDate;
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

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", importDate=" + importDate +
                ", expireDate=" + expireDate +
                ", sellPrice=" + sellPrice +
                ", importPrice=" + importPrice +
                '}';
    }
}

