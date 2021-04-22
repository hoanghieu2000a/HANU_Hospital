package se2.hanu_hospital.Medicine;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Column(length = 50)
    private String name;

//    @Column(nullable = false, updatable = false, insertable = false,
//            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDate importDate;

    private LocalDate expireDate;

    @Column(precision = 8, scale = 2)
    private Double sellPrice;

    @Column(precision = 8, scale = 2)
    private Double importPrice;

    @Column(columnDefinition = "INT(4) UNSIGNED")
    private int quantity;


    public Medicine(Long id, String name, LocalDate expireDate, Double sellPrice, Double importPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.expireDate = expireDate;
        this.sellPrice = sellPrice;
        this.importPrice = importPrice;
        this.quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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


}

