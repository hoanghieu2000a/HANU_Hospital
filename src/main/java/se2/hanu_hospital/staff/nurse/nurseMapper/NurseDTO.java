package se2.hanu_hospital.staff.nurse.nurseMapper;

import java.time.LocalDate;

public class NurseDTO {
    private String name;
    private Long id;
    private String phone;
    private String email;
    private LocalDate dob;
    private Double salary;

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
}
