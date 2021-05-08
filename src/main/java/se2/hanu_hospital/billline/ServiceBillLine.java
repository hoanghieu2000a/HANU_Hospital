package se2.hanu_hospital.billline;


import com.fasterxml.jackson.annotation.JsonIgnore;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ServiceBillLine extends BillLine {

    @OneToOne()
    @JoinColumn(name = "medicalProcedure")
    @JsonIgnore
    private MedicalProcedure medicalProcedure;

    public MedicalProcedure getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(MedicalProcedure procedure) {
        this.medicalProcedure = procedure;
    }
}
