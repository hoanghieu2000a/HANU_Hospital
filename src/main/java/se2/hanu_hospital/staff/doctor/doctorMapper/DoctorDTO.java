package se2.hanu_hospital.staff.doctor.doctorMapper;

public class DoctorDTO {
    private String name;
    private Long id;
    private String phone;
    private String email;
    private Integer age;
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

    public Integer getAge() {
        return age;
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
}
