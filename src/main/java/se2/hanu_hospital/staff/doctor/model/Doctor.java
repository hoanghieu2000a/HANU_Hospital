package se2.hanu_hospital.staff.doctor.model;

import se2.hanu_hospital.staff.Staff;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor extends Staff {
    @Column(length = 255)
    private String speciality;
    @Column(length = 255)
    private String department;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean available;

    public Doctor(String name, Long id, String phone, String email, Integer age, String speciality, String department, boolean available) {
        super(name, id, phone, email, age);
        this.speciality = speciality;
        this.department = department;
        this.available = available;
    }

    public Doctor(){

    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
