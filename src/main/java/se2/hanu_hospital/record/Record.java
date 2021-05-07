package se2.hanu_hospital.record;


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
    @Lob
    private String diagnosis = "This record does not have diagnosis yet";

    private RecordStatus status;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "user_id")
    private Long patientId;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "user_id")
    private Long doctorId;

    @OneToMany(mappedBy = "record")
    private Set<Prescription> prescriptionMedicine = new HashSet<>();

    public Record(Long id, LocalDate date, String description, String diagnosis, RecordStatus status, Long patientId, Long doctorId, Set<Prescription> prescriptionMedicine) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.diagnosis =diagnosis;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionMedicine = prescriptionMedicine;
    }

    public Record() {
    }
    public Set<Prescription> getPrescriptionMedicine() {
        return prescriptionMedicine;
    }

    public void setPrescriptionMedicine(Set<Prescription> prescriptionMedicine) {
        this.prescriptionMedicine = prescriptionMedicine;
    }
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
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
