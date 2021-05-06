package se2.hanu_hospital.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.department.Department;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "department")
    @JsonIgnore
    private Department department;

    public Staff() {
    }

    public Staff(String name, Long id, String phone, String email, Integer age, Department department) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.department = department;
    }

    public Staff(String name, Long id, String phone, String email, Integer age) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public Staff(String name, String phone, String email, Integer age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
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
