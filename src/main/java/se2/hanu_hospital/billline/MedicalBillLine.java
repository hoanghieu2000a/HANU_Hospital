package se2.hanu_hospital.billline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import se2.hanu_hospital.medicine.Medicine;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
public class MedicalBillLine extends BillLine {

    @OneToOne
    private Medicine medicine;
}
