package se2.hanu_hospital.staff.doctor.doctorMapper;

import java.time.LocalDate;

public class DoctorDTO {
    private String name;
    private Long id;
    private String phone;
    private String email;
    private LocalDate dob;
    private Double salary;
    private String speciality;
    private boolean available;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Double getSalary() {
        return salary;
    }

    public String getSpeciality() {
        return speciality;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
