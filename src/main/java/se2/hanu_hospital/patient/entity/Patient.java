package se2.hanu_hospital.patient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se2.hanu_hospital.patient.entity.Gender;
import se2.hanu_hospital.room.Room;


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
public class Patient{

    @Id
    @Column(name = "id", columnDefinition = "INT(6) UNSIGNED ", precision = 4, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(max = 100)
    private String name;

    private Gender gender;

    @PastOrPresent
    private LocalDate dob;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;


//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "patient_details_id")
//    private PatientDetails patientDetails;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
    
}
