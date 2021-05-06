package se2.hanu_hospital.patient.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se2.hanu_hospital.util.Gender;
import se2.hanu_hospital.util.TimeStamps;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CreatePatientDTO implements TimeStamps {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Gender gender;

    @PastOrPresent
    private LocalDate dob;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;


}
