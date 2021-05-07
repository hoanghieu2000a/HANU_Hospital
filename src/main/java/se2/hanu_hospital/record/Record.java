package se2.hanu_hospital.record;


import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.staff.doctor.model.Doctor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @Lob
    private String description = "This record does not have description";
    @Lob
    private String diagnosis = "This record does not have diagnosis yet";

    private RecordStatus status;

    @ManyToOne(optional = true)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(optional = true)
    @JoinColumn(name = "patient_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "record")
    private Set<Prescription> prescriptionMedicine = new HashSet<>();

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public Record(Long id, String description, String diagnosis, RecordStatus status, Patient patient, Doctor doctor, Set<Prescription> prescriptionMedicine) {
        this.id = id;
        this.description = description;
        this.diagnosis =diagnosis;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
