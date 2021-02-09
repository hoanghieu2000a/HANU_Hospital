package se2.hanu_hospital.model;

import java.util.Date;

public class MedicalRecord {
    private int id;
    private Patient patient;
    private DrugList drugList;
    private String disease, treatmentNote;
    private Date treatmentDate;

    public MedicalRecord(int id, Patient patient, DrugList drugList, String disease, String treatmentNote, Date treatmentDate) {
        this.id = id;
        this.patient = patient;
        this.drugList = drugList;
        this.disease = disease;
        this.treatmentNote = treatmentNote;
        this.treatmentDate = treatmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DrugList getDrugList() {
        return drugList;
    }

    public void setDrugList(DrugList drugList) {
        this.drugList = drugList;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTreatmentNote() {
        return treatmentNote;
    }

    public void setTreatmentNote(String treatmentNote) {
        this.treatmentNote = treatmentNote;
    }

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(Date treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patient=" + patient +
                ", drugList=" + drugList +
                ", disease='" + disease + '\'' +
                ", treatmentNote='" + treatmentNote + '\'' +
                ", treatmentDate=" + treatmentDate +
                '}';
    }
}
