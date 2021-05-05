package se2.hanu_hospital.staff.receptionist.model;

import se2.hanu_hospital.staff.Staff;

import javax.persistence.*;

@Entity
@Table(name = "receptionist")
public class Receptionist extends Staff {
    public Receptionist() {
    }

    public Receptionist(String name, Long id, String phone, String email, Integer age) {
        super(name, id, phone, email, age);
    }

    public Receptionist(String name, String phone, String email, Integer age) {
        super(name, phone, email, age);
    }
}
