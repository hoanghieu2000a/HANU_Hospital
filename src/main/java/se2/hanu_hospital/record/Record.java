package se2.hanu_hospital.record;


import se2.hanu_hospital.medicine.RecordStatus;
import se2.hanu_hospital.prescription.Prescription;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    private LocalDate date;

    @Lob
    private String description = "This record does not have description";

    private RecordStatus status;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "user_id")
    private Long patientId;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "user_id")
    private Long doctorId;

//    @OneToMany(mappedBy = "record")
//    private Set<Prescription> prescriptionMedicine = new HashSet<>();

    public Record(Long id, LocalDate date, String description, RecordStatus status, Long patientId, Long doctorId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Record() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

}
