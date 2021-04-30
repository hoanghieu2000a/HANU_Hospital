package com.example.api.core.billline;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class ServiceBillLine extends BillLine {

    private String serviceName;
}
