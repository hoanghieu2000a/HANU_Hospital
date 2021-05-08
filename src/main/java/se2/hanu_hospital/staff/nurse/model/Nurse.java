package se2.hanu_hospital.staff.nurse.model;

import se2.hanu_hospital.staff.Staff;

import javax.persistence.*;

@Entity
@Table(name = "nurse")
public class Nurse extends Staff {
    public Nurse() {
    }

    public Nurse(String name, Long id, String phone, String email, Integer age, Double salary) {
        super(name, id, phone, email, age, salary);
    }

    public Nurse(String name, String phone, String email, Integer age, Double salary) {
        super(name, phone, email, age, salary);
    }
}
