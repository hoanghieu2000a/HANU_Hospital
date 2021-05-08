package se2.hanu_hospital.billline;


import se2.hanu_hospital.medical_procedure.MedicalProcedure;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ServiceBillLine extends BillLine {

    @OneToOne
    private MedicalProcedure medicalProcedure;
}
