package se2.hanu_hospital.billline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.bill.Bill;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BillLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected double price;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "bill")
    @JsonIgnore
    private Bill bill;

    public BillLine(Long id, double price) {
        this.id = id;
        this.price = price;
    }

    public BillLine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "BillLine{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
