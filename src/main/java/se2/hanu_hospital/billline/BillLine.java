package se2.hanu_hospital.billline;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BillLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected double price;

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

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillLine{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
