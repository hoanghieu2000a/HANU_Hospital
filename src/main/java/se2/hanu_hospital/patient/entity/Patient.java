package se2.hanu_hospital.domain.patient.entity;


import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se2.hanu_hospital.base.TimeStamps;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Patient implements TimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "patient_details_id")
//    private PatientDetails patientDetails;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
