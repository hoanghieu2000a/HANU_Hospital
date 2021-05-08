package se2.hanu_hospital.staff.nurse.nurseMapper;

public class NurseDTO {
    private String name;
    private Long id;
    private String phone;
    private String email;
    private Integer age;
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

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }
}
