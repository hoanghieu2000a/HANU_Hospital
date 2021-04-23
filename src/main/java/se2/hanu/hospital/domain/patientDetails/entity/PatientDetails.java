package se2.hanu.hospital.domain.patientDetails.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se2.hanu.hospital.domain.patient.entity.Patient;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_details")
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String bloodtype;

    private int heigth;

    private int weigth;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "patient_id")
//    private Patient patient;

}
