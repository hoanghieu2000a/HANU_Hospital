package se2.hanu_hospital.department;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import se2.hanu_hospital.staff.Staff;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "department")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Staff> staff = new HashSet<>();

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Long id, String name, Set<Staff> staff) {
        this.id = id;
        this.name = name;
        this.staff = staff;
    }

    public Department() {

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

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", staff=" + staff +
                '}';
    }
}
