package com.example.api.core.billline;

import com.example.api.core.drug.Drug;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
public class MedicalBillLine extends BillLine {

    @OneToOne
    private Drug drug;
}
