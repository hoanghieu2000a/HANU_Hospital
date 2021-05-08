package se2.hanu_hospital.patient;

import se2.hanu_hospital.util.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PatientPayload {
    @Size(max = 100)
    private String name;

    private Gender gender;

    private LocalDate dob;

    private String phoneNumber;

    private String address;

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
