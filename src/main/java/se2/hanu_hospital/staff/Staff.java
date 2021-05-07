package se2.hanu_hospital.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.account.model.User;
import se2.hanu_hospital.department.Department;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Staff {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String phone;
    private String email;
    private Integer age;
    private Double salary;
    @ManyToOne
    @JoinColumn(name = "department")
    @JsonIgnore
    private Department department;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToOne()
    private User user;

    public Staff() {
    }

    public Staff(String name, Long id, String phone, String email, Integer age, Double salary, Department department) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public Staff(String name, Long id, String phone, String email, Integer age, Double salary) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.salary = salary;
    }

    public Staff(String name, String phone, String email, Integer age, Double salary) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
