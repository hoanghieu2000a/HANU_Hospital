package se2.hanu_hospital.patient.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Builder(toBuilder = true)
public class Patient  {

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

    public Patient(long id, String name, Gender gender, LocalDate dob, String phoneNumber, String address, Room room, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.room = room;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
