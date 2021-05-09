package se2.hanu_hospital.billline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.prescription.Prescription;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MedicalBillLine extends BillLine {

    @OneToOne()
    @JoinColumn(name = "prescription")
    private Prescription prescription;

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
